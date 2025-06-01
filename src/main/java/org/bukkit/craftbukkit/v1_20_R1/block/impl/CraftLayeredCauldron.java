/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftLayeredCauldron extends CraftBlockData implements org.bukkit.block.data.Levelled {

    public CraftLayeredCauldron() {
        super();
    }

    public CraftLayeredCauldron(net.minecraft.world.level.block.state.BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.CraftLevelled

    private static final net.minecraft.world.level.block.state.properties.IntegerProperty LEVEL = getInteger(net.minecraft.world.level.block.LayeredCauldronBlock.class, "level");

    @Override
    public int getLevel() {
        if (getState() != null && getState().getBlock() instanceof net.minecraft.world.level.block.LayeredCauldronBlock layeredCauldron)
            return getOrFallback(LEVEL, layeredCauldron.getLevelProperty());
        else
            return get(LEVEL);
    }

    @Override
    public void setLevel(int level) {
        if (getState() != null && getState().getBlock() instanceof net.minecraft.world.level.block.LayeredCauldronBlock layeredCauldron)
            setOrFallback(LEVEL, layeredCauldron.getLevelProperty(), level);
        else
            set(LEVEL, level);
    }

    @Override
    public int getMaximumLevel() {
        if (getState() != null && getState().getBlock() instanceof net.minecraft.world.level.block.LayeredCauldronBlock layeredCauldron)
            return layeredCauldron.getLevelProperty().max;
        else
            return getMax(LEVEL);
    }
}
