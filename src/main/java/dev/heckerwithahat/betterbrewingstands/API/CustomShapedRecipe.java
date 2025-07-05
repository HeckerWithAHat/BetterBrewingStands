package dev.heckerwithahat.betterbrewingstands.API;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class CustomShapedRecipe extends ShapedRecipe {
    public CustomShapedRecipe(NamespacedKey key, CustomItem result, String[] shape, RecipeIngredient... ingredients) {
        super(key, result);
        this.shape(shape[0],shape[1],shape[2]);
        for (RecipeIngredient ingredient : ingredients) {
            this.setIngredient(ingredient.symbol, ingredient.item);
        }
        BetterBrewingStands.getPlugin(BetterBrewingStands.class).getServer().addRecipe(this);
    }
}
