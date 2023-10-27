/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.common.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

/**
 * Noop biome modifier. Can be used in a biome modifier json with "type": "forge:none".
 */
public class NoneBiomeModifier implements BiomeModifier {
    public static final NoneBiomeModifier INSTANCE = new NoneBiomeModifier();
    public static final Codec<NoneBiomeModifier> CODEC = Codec.unit(NoneBiomeModifier.INSTANCE);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
