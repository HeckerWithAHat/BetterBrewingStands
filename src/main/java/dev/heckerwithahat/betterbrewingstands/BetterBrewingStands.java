package dev.heckerwithahat.betterbrewingstands;

import dev.heckerwithahat.betterbrewingstands.API.CustomItem;
import dev.heckerwithahat.betterbrewingstands.API.CustomRecipe;
import dev.heckerwithahat.betterbrewingstands.API.RecipeIngredient;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public final class BetterBrewingStands extends JavaPlugin {

    public static CustomItem LEVEL_BLOCK;
    public static CustomItem WATER_BLOCK;
    public static CustomItem TIME_BLOCK;
    public static CustomItem SPEED_BLOCK;

    public static CustomRecipe LEVEL_BLOCK_RECIPE;
    public static CustomRecipe WATER_BLOCK_RECIPE;
    public static CustomRecipe TIME_BLOCK_RECIPE;
    public static CustomRecipe SPEED_BLOCK_RECIPE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("BetterBrewingStands has been enabled!");
        // Register commands, listeners, etc. here
        for (Class<?> clazz : new Reflections(getClass().getPackage().getName() + ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                Bukkit.getServer().getConsoleSender().sendMessage(clazz.getName());

                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        LEVEL_BLOCK = new CustomItem(org.bukkit.Material.DIAMOND_BLOCK, 1, "Level Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Increases the potency of all potions brewed in the stand"}, true, "level");
        WATER_BLOCK = new CustomItem(org.bukkit.Material.LAPIS_BLOCK, 1, "Water Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Provide infinite water to the brewing stand"}, true, "water");
        TIME_BLOCK = new CustomItem(org.bukkit.Material.GOLD_BLOCK, 1, "Time Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Increases the effect time of all potions brewed in the stand"}, true, "time");
        SPEED_BLOCK = new CustomItem(org.bukkit.Material.COAL_BLOCK, 1, "Speed Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Increases the brewing speed of all potions brewed in the stand"}, true, "speed");


        LEVEL_BLOCK_RECIPE = new CustomRecipe(NamespacedKey.fromString("upgradelevelrecipe", this), LEVEL_BLOCK, new String[]{"XZX", "ZYZ", "XZX"},
                new RecipeIngredient('X', new RecipeChoice.MaterialChoice(Material.DIAMOND_BLOCK)),
                new RecipeIngredient('Y', new RecipeChoice.MaterialChoice(Material.GHAST_TEAR)),
                new RecipeIngredient('Z', new RecipeChoice.MaterialChoice(Material.BLAZE_POWDER)));
        WATER_BLOCK_RECIPE = new CustomRecipe(NamespacedKey.fromString("upgradewaterrecipe", this), WATER_BLOCK, new String[]{"XZX", "ZYZ", "XZX"},
                new RecipeIngredient('X', new RecipeChoice.MaterialChoice(Material.LAPIS_BLOCK)),
                new RecipeIngredient('Y', new RecipeChoice.MaterialChoice(Material.WATER_BUCKET)),
                new RecipeIngredient('Z', new RecipeChoice.MaterialChoice(Material.BLAZE_POWDER)));
        TIME_BLOCK_RECIPE = new CustomRecipe(NamespacedKey.fromString("upgradetimerecipe", this), TIME_BLOCK, new String[]{"XZX", "ZYZ", "XZX"},
                new RecipeIngredient('X', new RecipeChoice.MaterialChoice(Material.GOLD_BLOCK)),
                new RecipeIngredient('Y', new RecipeChoice.MaterialChoice(Material.CLOCK)),
                new RecipeIngredient('Z', new RecipeChoice.MaterialChoice(Material.BLAZE_POWDER)));
        SPEED_BLOCK_RECIPE = new CustomRecipe(NamespacedKey.fromString("upgradespeedrecipe", this), SPEED_BLOCK, new String[]{"XZX", "ZYZ", "XZX"},
                new RecipeIngredient('X', new RecipeChoice.MaterialChoice(Material.COAL_BLOCK)),
                new RecipeIngredient('Y', new RecipeChoice.MaterialChoice(Material.SUGAR)),
                new RecipeIngredient('Z', new RecipeChoice.MaterialChoice(Material.BLAZE_POWDER)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        // Handle commands here
        if (command.getName().equalsIgnoreCase("levelblock")) {
            ((Player) sender).getInventory().addItem(LEVEL_BLOCK);
            return true;
        }
        return false;
    }
}
