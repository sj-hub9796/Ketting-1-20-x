package org.kettingpowered.ketting.craftbukkit;

import io.izzel.tools.collection.XmapList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventConverter {

    public static List<ItemStack> createXmapList(Collection<ItemEntity> drops, LivingEntity entity) {
        return XmapList.create((List<ItemEntity>) drops, ItemStack.class,
                (ItemEntity e) -> CraftItemStack.asCraftMirror(e.getItem()),
                itemStack -> {
                    ItemEntity itemEntity = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), CraftItemStack.asNMSCopy(itemStack));
                    itemEntity.setDefaultPickUpDelay();
                    return itemEntity;
                });
    }

    public static LivingDropsEvent callLivingDropsEvent(LivingEntity entity, DamageSource source, Collection<ItemEntity> drops, int lootingLevel, boolean recentlyHit) {
        LivingDropsEvent event = new LivingDropsEvent(entity, source, drops, lootingLevel, recentlyHit);
        if (entity instanceof ServerPlayer) return event;

        if (!(drops instanceof ArrayList))
            drops = new ArrayList<>(drops);

        List<ItemStack> bukkitDrops = createXmapList(drops, entity);

        CraftLivingEntity craftLivingEntity = ((CraftLivingEntity) entity.getBukkitEntity());
        EntityDeathEvent bukkitEvent = new EntityDeathEvent(craftLivingEntity, bukkitDrops, entity.getExpReward());
        Bukkit.getPluginManager().callEvent(bukkitEvent);
        entity.expToDrop = bukkitEvent.getDroppedExp();

        if (drops.isEmpty())
            event.setCanceled(true);

        return event;
    }
}
