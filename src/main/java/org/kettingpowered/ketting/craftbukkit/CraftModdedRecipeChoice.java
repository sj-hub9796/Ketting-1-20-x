package org.kettingpowered.ketting.craftbukkit;

import net.minecraft.world.item.crafting.Ingredient;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public class CraftModdedRecipeChoice implements RecipeChoice {

    private final Ingredient ingredient;

    public CraftModdedRecipeChoice(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public @NotNull ItemStack getItemStack() {
        net.minecraft.world.item.ItemStack[] items = ingredient.getItems();
        return items.length > 0 ? CraftItemStack.asCraftMirror(items[0]) : new ItemStack(Material.AIR, 0);
    }

    public @NotNull RecipeChoice clone() {
        try {
            return (RecipeChoice) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Could not clone recipe", e);
        }
    }

    public boolean test(@NotNull ItemStack itemStack) {
        return ingredient.test(CraftItemStack.asNMSCopy(itemStack));
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
