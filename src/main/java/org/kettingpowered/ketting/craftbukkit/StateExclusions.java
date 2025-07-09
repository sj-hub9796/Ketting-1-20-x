package org.kettingpowered.ketting.craftbukkit;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * thanks amendments for overriding the vanilla cauldron block...
 */
public class StateExclusions {

    private static final Map<Class<?>, Set<String>> stateExclusions = new HashMap<>();

    static {
        stateExclusions.computeIfAbsent(net.minecraft.world.level.block.LayeredCauldronBlock.class, k -> new HashSet<>())
                .add("net.mehvahdjukaar.amendments.common.block.BoilingWaterCauldronBlock");

        stateExclusions.computeIfAbsent(net.minecraft.world.level.block.FarmBlock.class, k -> new HashSet<>())
                .add("cool.bot.dewdropfarmland.block.CustomFarmland");
    }

    public static boolean isExcluded(Class<?> block, Block instance) {
        Set<String> excludedClasses = stateExclusions.get(block);
        return excludedClasses != null && excludedClasses.contains(instance.getClass().getName());
    }
}
