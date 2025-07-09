/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSoil extends CraftBlockData implements org.bukkit.block.data.type.Farmland {

    public CraftSoil() {
        super();
    }

    public CraftSoil(net.minecraft.world.level.block.state.BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftFarmland

    private static final net.minecraft.world.level.block.state.properties.IntegerProperty MOISTURE = getInteger(net.minecraft.world.level.block.FarmBlock.class, "moisture");

    @Override
    public int getMoisture() {
        if (getState() != null && getState().getBlock() instanceof net.minecraft.world.level.block.FarmBlock farmBlock) {
            return getOrFallback(MOISTURE, farmBlock.getMoistureProperty());
        } else {
            return get(MOISTURE);
        }
    }

    @Override
    public void setMoisture(int moisture) {
        if (getState() != null && getState().getBlock() instanceof net.minecraft.world.level.block.FarmBlock farmBlock) {
            setOrFallback(MOISTURE, farmBlock.getMoistureProperty(), moisture);
        } else {
            set(MOISTURE, moisture);
        }
    }

    @Override
    public int getMaximumMoisture() {
        if (getState() != null && getState().getBlock() instanceof net.minecraft.world.level.block.FarmBlock farmBlock) {
            return farmBlock.getMoistureProperty().max;
        } else {
            return getMax(MOISTURE);
        }
    }
}
