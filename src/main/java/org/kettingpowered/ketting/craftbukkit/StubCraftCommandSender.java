package org.kettingpowered.ketting.craftbukkit;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.command.ServerCommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public final class StubCraftCommandSender implements CommandSender {
    public static final StubCraftCommandSender INSTANCE = new StubCraftCommandSender(); 
    private StubCraftCommandSender(){}
    @Override
    public void sendMessage(@NotNull String message) {}

    @Override
    public void sendMessage(@NotNull String... messages) {}

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {}

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String... messages) {}

    @Override
    public @NotNull Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public @NotNull String getName() {
        return "StubCraftCommandSender";
    }

    @Override
    public @NotNull Spigot spigot() {
        return spigot;
    }

    private static final org.bukkit.command.CommandSender.Spigot spigot = new org.bukkit.command.CommandSender.Spigot()
    {
        @Override
        public void sendMessage(net.md_5.bungee.api.chat.BaseComponent component)
        {}

        @Override
        public void sendMessage(net.md_5.bungee.api.chat.BaseComponent... components)
        {}

        @Override
        public void sendMessage(UUID sender, net.md_5.bungee.api.chat.BaseComponent... components)
        {}

        @Override
        public void sendMessage(UUID sender, net.md_5.bungee.api.chat.BaseComponent component)
        {}
    };

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        return false;
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return false;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        throw new UnsupportedOperationException("Not supported on StubCraftCommandSender");
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        throw new UnsupportedOperationException("Not supported on StubCraftCommandSender");
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return null;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {
        throw new UnsupportedOperationException("Not supported on StubCraftCommandSender");
    }

    @Override
    public void recalculatePermissions() {}

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return Set.of();
    }

    @Override
    public boolean isOp() {
        return false;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Not supported on StubCraftCommandSender");
    }
}
