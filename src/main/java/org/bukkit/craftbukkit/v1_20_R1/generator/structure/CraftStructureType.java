package org.bukkit.craftbukkit.v1_20_R1.generator.structure;

import net.minecraft.core.registries.BuiltInRegistries;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.generator.structure.StructureType;
import org.kettingpowered.ketting.config.KettingConfig;
import org.kettingpowered.ketting.core.Ketting;

public class CraftStructureType extends StructureType {

    public static StructureType minecraftToBukkit(net.minecraft.world.level.levelgen.structure.StructureType<?> minecraft) {
        if (minecraft == null) {
            return null;
        }

        //Ketting start - fallback to MODDED if the key is not present in the registry
        var key = BuiltInRegistries.STRUCTURE_TYPE.getKey(minecraft);

        if (key == null) {
            if (KettingConfig.getInstance().WARN_ON_UNKNOWN_STRUCTURE_TYPE.getValue())
                Ketting.LOGGER.warn("Structure type with class {} was unknown, likely modded", minecraft.getClass().getName());
            return StructureType.MODDED;
        }

        return Registry.STRUCTURE_TYPE.get(CraftNamespacedKey.fromMinecraft(key));
        //Ketting end
    }

    public static net.minecraft.world.level.levelgen.structure.StructureType<?> bukkitToMinecraft(StructureType bukkit) {
        if (bukkit == null) {
            return null;
        }

        return ((CraftStructureType) bukkit).getHandle();
    }

    private final NamespacedKey key;
    private final net.minecraft.world.level.levelgen.structure.StructureType<?> structureType;

    public CraftStructureType(NamespacedKey key, net.minecraft.world.level.levelgen.structure.StructureType<?> structureType) {
        this.key = key;
        this.structureType = structureType;
    }

    public net.minecraft.world.level.levelgen.structure.StructureType<?> getHandle() {
        return structureType;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
