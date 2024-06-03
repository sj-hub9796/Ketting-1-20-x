package org.kettingpowered.ketting.craftbukkit;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftComplexRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CraftModdedRecipe extends CraftComplexRecipe {

    private final Recipe<?> recipe;

    public CraftModdedRecipe(Recipe<?> recipe) {
        super(null);
        this.recipe = recipe;
    }

    public @NotNull ItemStack getResult() {
        return CraftItemStack.asCraftMirror(this.recipe.getResultItem(ServerLifecycleHooks.getCurrentServer().registryAccess()));
    }

    public @NotNull NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(this.recipe.getId());
    }

    public void addToCraftingManager() {
        ServerLifecycleHooks.getCurrentServer().getRecipeManager().addRecipe(this.recipe);
    }
}
