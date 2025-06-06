package dev.heckerwithahat.betterBrewingStands;

import dev.heckerwithahat.betterBrewingStands.API.FullItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public final class BetterBrewingStands extends JavaPlugin {


    public static FullItem LEVEL_BLOCK;

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

        LEVEL_BLOCK = new FullItem(org.bukkit.Material.DIAMOND_BLOCK, 1, "Level Upgrade Block", new String[]{"This block is used to upgrade brewing stands", "Place it next to a brewing stand to upgrade it", "Provide it with a redstone signal to deactivate it", "Increases the potency of all potions brewed in the stand"}, true);
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
