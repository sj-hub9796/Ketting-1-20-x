package org.kettingpowered.ketting.entity;

import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRaider;

public class CraftCustomRaider extends CraftRaider {

    public CraftCustomRaider(CraftServer server, Raider entity) {
        super(server, entity);
    }
}
