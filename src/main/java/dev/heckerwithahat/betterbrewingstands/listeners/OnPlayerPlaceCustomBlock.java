package dev.heckerwithahat.betterBrewingStands.listeners;

import dev.heckerwithahat.betterBrewingStands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Conduit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

import static dev.heckerwithahat.betterBrewingStands.BetterBrewingStands.LEVEL_BLOCK;

public class OnPlayerPlaceCustomBlock implements Listener {
    @EventHandler
    public void onPlayerPlaceCustomBlock(BlockPlaceEvent event) {
        // This method will handle the event when a player places a custom block
        if (event.getItemInHand().equals(LEVEL_BLOCK)) {
            event.getPlayer().sendMessage("You placed a Level Upgrade Block!");
            event.getBlockPlaced().setType(Material.CONDUIT);
            Conduit block = (Conduit) event.getBlockPlaced();
            block.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("upgradetype", BetterBrewingStands.getPlugin(BetterBrewingStands.class))),
                    org.bukkit.persistence.PersistentDataType.STRING, "level");
        }
    }
}
