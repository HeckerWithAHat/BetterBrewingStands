package dev.heckerwithahat.betterbrewingstands.listeners;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Conduit;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;


public class OnPlayerPlaceCustomBlock implements Listener {
    @EventHandler
    public void onPlayerPlaceCustomBlock(BlockPlaceEvent event) {
        // This method will handle the event when a player places a custom block
        if (Objects.requireNonNull(event.getItemInHand().getItemMeta()).getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeitem", BetterBrewingStands.getPlugin(BetterBrewingStands.class))))) {
            event.getBlockPlaced().setType(Material.CONDUIT);
            Conduit block = (Conduit) event.getBlockPlaced().getWorld().getBlockAt(event.getBlockPlaced().getLocation()).getState();
            block.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock",
                            BetterBrewingStands.getPlugin(BetterBrewingStands.class))),
                    org.bukkit.persistence.PersistentDataType.STRING,
                    Objects.requireNonNull(event.getItemInHand().getItemMeta().getPersistentDataContainer().get(
                            Objects.requireNonNull(NamespacedKey.fromString("upgradetypeitem",
                                    BetterBrewingStands.getPlugin(BetterBrewingStands.class))),
                            PersistentDataType.STRING)));
            Waterlogged w = ((Waterlogged) block.getBlockData());
            w.setWaterlogged(false);
            block.setBlockData(w);
            block.update();
        }
    }
}
