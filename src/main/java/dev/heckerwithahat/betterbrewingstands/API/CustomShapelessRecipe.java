package dev.heckerwithahat.betterbrewingstands.API;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CustomShapelessRecipe extends ShapelessRecipe {
    public CustomShapelessRecipe(NamespacedKey key, CustomItem result, RecipeChoice... ingredients) {
        super(key, result);
        for (RecipeChoice ingredient : ingredients)
            this.addIngredient(ingredient);

        BetterBrewingStands.getPlugin(BetterBrewingStands.class).getServer().addRecipe(this);
    }
}
