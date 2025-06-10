package dev.heckerwithahat.betterbrewingstands.API;

import org.bukkit.inventory.RecipeChoice;

public class RecipeIngredient {
    public final char symbol;
    public final RecipeChoice item;

    public RecipeIngredient(char symbol, RecipeChoice item) {
        this.symbol = symbol;
        this.item = item;
    }
}
