package dev.heckerwithahat.betterBrewingStands.listeners;

import dev.heckerwithahat.betterBrewingStands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Conduit;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Arrays;
import java.util.Objects;

import static dev.heckerwithahat.betterBrewingStands.BetterBrewingStands.LEVEL_BLOCK;

public class OnPlayerPlaceCustomBlock implements Listener {
    @EventHandler
    public void onPlayerPlaceCustomBlock(BlockPlaceEvent event) {
        // This method will handle the event when a player places a custom block
        if (Objects.requireNonNull(event.getItemInHand().getItemMeta()).getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeitem", BetterBrewingStands.getPlugin(BetterBrewingStands.class))))) {
            event.getPlayer().sendMessage("You placed an Upgrade Block!");
            event.getBlockPlaced().setType(Material.CONDUIT);
            Conduit block = (Conduit) event.getBlockPlaced().getWorld().getBlockAt(event.getBlockPlaced().getLocation()).getState();
            block.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock", BetterBrewingStands.getPlugin(BetterBrewingStands.class))),
                    org.bukkit.persistence.PersistentDataType.STRING, "level");
            event.getPlayer().sendMessage(String.valueOf(block.getBlockData() instanceof Waterlogged) +  ((Waterlogged)block.getBlockData()).isWaterlogged());
            Waterlogged w = ((Waterlogged)block.getBlockData());
            w.setWaterlogged(false);
            block.setBlockData(w);
            block.update();
            event.getPlayer().sendMessage(String.valueOf(block.getBlockData() instanceof Waterlogged) +  ((Waterlogged)block.getBlockData()).isWaterlogged());
            block.update();
        }
    }
}
