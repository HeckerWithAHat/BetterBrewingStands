package dev.heckerwithahat.betterbrewingstands;

import dev.heckerwithahat.betterbrewingstands.API.CustomItem;
import dev.heckerwithahat.betterbrewingstands.API.CustomShapedRecipe;
import dev.heckerwithahat.betterbrewingstands.API.CustomShapelessRecipe;
import dev.heckerwithahat.betterbrewingstands.API.RecipeIngredient;
import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public final class BetterBrewingStands extends JavaPlugin {

    public static CustomItem LEVEL_BLOCK;
    public static CustomItem TIME_BLOCK;

    public static CustomShapedRecipe LEVEL_BLOCK_RECIPE;
    public static CustomShapedRecipe TIME_BLOCK_RECIPE;
    public static CustomShapelessRecipe SPLASH_WATER_BOTTLE_RECIPE;
    public static CustomShapelessRecipe LINGER_WATER_BOTTLE_RECIPE;



    @Override
    public void onEnable() {
        saveDefaultConfig();
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
        new InventoryAPI(this).init();
        LEVEL_BLOCK = new CustomItem(org.bukkit.Material.DIAMOND_BLOCK, 1, "Level Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Increases the potency of all potions brewed in the stand"}, true, "level");
        TIME_BLOCK = new CustomItem(org.bukkit.Material.GOLD_BLOCK, 1, "Time Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Increases the effect time of all potions brewed in the stand"}, true, "time");




        CustomItem splashWaterBottle = new CustomItem(Material.SPLASH_POTION, 1, "Splash Water Bottle", new String[]{}, true);
        PotionMeta splashMeta = (PotionMeta) splashWaterBottle.getItemMeta();
        splashMeta.setBasePotionType(PotionType.WATER);
        splashWaterBottle.setItemMeta(splashMeta);
        CustomItem lingeringWaterBottle = new CustomItem(Material.LINGERING_POTION, 1, "Lingering Water Bottle", new String[]{}, true);
        PotionMeta lingeringMeta = (PotionMeta) lingeringWaterBottle.getItemMeta();
        lingeringMeta.setBasePotionType(PotionType.WATER);
        lingeringWaterBottle.setItemMeta(lingeringMeta);




        LEVEL_BLOCK_RECIPE = new CustomShapedRecipe(NamespacedKey.fromString("upgradelevelrecipe", this), LEVEL_BLOCK, new String[]{"XZX", "ZYZ", "XZX"},
                new RecipeIngredient('X', new RecipeChoice.MaterialChoice(Material.DIAMOND_BLOCK)),
                new RecipeIngredient('Y', new RecipeChoice.MaterialChoice(Material.GHAST_TEAR)),
                new RecipeIngredient('Z', new RecipeChoice.MaterialChoice(Material.BLAZE_POWDER)));

        TIME_BLOCK_RECIPE = new CustomShapedRecipe(NamespacedKey.fromString("upgradetimerecipe", this), TIME_BLOCK, new String[]{"XZX", "ZYZ", "XZX"},
                new RecipeIngredient('X', new RecipeChoice.MaterialChoice(Material.GOLD_BLOCK)),
                new RecipeIngredient('Y', new RecipeChoice.MaterialChoice(Material.CLOCK)),
                new RecipeIngredient('Z', new RecipeChoice.MaterialChoice(Material.BLAZE_POWDER)));

        SPLASH_WATER_BOTTLE_RECIPE = new CustomShapelessRecipe(
                NamespacedKey.fromString("splashwaterbottlerecipe", this),
                splashWaterBottle,
                new RecipeChoice.MaterialChoice(Material.GUNPOWDER),
                new RecipeChoice.MaterialChoice(Material.POTION)
        );

        LINGER_WATER_BOTTLE_RECIPE = new CustomShapelessRecipe(
                NamespacedKey.fromString("lingeringwaterbottlerecipe", this),
                lingeringWaterBottle,
                new RecipeChoice.MaterialChoice(Material.DRAGON_BREATH),
                new RecipeChoice.MaterialChoice(Material.POTION)
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
