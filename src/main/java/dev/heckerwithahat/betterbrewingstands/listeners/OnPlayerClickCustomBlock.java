package dev.heckerwithahat.betterBrewingStands.listeners;

import dev.heckerwithahat.betterBrewingStands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Conduit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class OnPlayerClickCustomBlock implements Listener {

    @EventHandler
    public void onPlayerClickCustomBlock(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND || event.getClickedBlock() == null || !event.getClickedBlock().getType().equals(Material.CONDUIT)) {
            return; // Ignore off-hand interaction, non-block interactions, or non-conduit blocks
        }

        Player player = event.getPlayer();
        Conduit conduit = (Conduit) event.getClickedBlock().getState();

        if (!conduit.getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock", BetterBrewingStands.getPlugin(BetterBrewingStands.class))))) return;

        player.sendMessage("This block is a BBS upgrade block!");

        switch (Objects.requireNonNull(conduit.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock", BetterBrewingStands.getPlugin(BetterBrewingStands.class))), PersistentDataType.STRING))) {
            case "level" -> {
                player.sendMessage("This is a Level Upgrade Block!");
                // Add your logic for level upgrade block interaction here
            }
            case "speed" -> {
                player.sendMessage("This is a Speed Upgrade Block!");
                // Add your logic for speed upgrade block interaction here
            }
            case "time" -> {
                player.sendMessage("This is a Time Upgrade Block!");
                // Add your logic for time upgrade block interaction here
            }
            case "water" -> {
                player.sendMessage("This is a Water Upgrade Block!");
                // Add your logic for water upgrade block interaction here
            }
            default -> {
                player.sendMessage("Unknown upgrade block type! Please contact the developer.");
                // Handle unknown upgrade block types if necessary
            }

        }

    }
}
