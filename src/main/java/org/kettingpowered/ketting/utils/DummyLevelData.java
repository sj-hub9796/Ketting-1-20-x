package org.kettingpowered.ketting.utils;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.PrimaryLevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import org.kettingpowered.ketting.core.Ketting;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DummyLevelData extends PrimaryLevelData {

    private static final LevelSettings DUMMY_SETTINGS = new LevelSettings("dummy", GameType.SURVIVAL, false, Difficulty.NORMAL, false, new GameRules(), WorldDataConfiguration.DEFAULT);
    private static final WorldOptions DUMMY_OPTIONS = WorldOptions.defaultWithRandomSeed();
    private static final Lifecycle DUMMY_LIFECYCLE = Lifecycle.stable();

    public DummyLevelData(LevelSettings p_251081_, WorldOptions p_251666_, Lifecycle p_251714_) {
        super(p_251081_, p_251666_, PrimaryLevelData.SpecialWorldProperty.NONE, p_251714_);
    }

    public DummyLevelData() {
        this(DUMMY_SETTINGS, DUMMY_OPTIONS, DUMMY_LIFECYCLE);
    }

    private static Map<ServerLevelData, PrimaryLevelData> mapped = new HashMap<>();

    public static PrimaryLevelData from(ServerLevelData data) {
        return mapped.computeIfAbsent(data, DummyLevelData::parse);
    }

    private static PrimaryLevelData parse(ServerLevelData data) {
        try {
            Field wrapped = data.getClass().getDeclaredField("wrapped");
            wrapped.setAccessible(true);
            ServerLevelData levelData = (ServerLevelData) wrapped.get(data);

            if (levelData instanceof PrimaryLevelData primary) return primary;
            else if (levelData instanceof DerivedLevelData derived) return org.kettingpowered.ketting.utils.DelegateWorldInfo.wrap(derived);
            else throw new Exception("Unknown level data type");
        } catch (Exception e) {
            Ketting.LOGGER.warn("Failed to extract wrapped level data, using dummy");
            return new DummyLevelData();
        }
    }
}
