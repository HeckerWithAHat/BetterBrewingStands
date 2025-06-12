package dev.heckerwithahat.betterbrewingstands.listeners;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import dev.heckerwithahat.betterbrewingstands.GUIs.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Conduit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class OnPlayerClickCustomBlock implements Listener {

    @EventHandler
    public void onPlayerClickCustomBlock(PlayerInteractEvent event) {
        /* Avoid double click, and force to be custom upgrade block */  if (event.getHand() != EquipmentSlot.HAND || event.getClickedBlock() == null || !event.getClickedBlock().getType().equals(Material.CONDUIT) || !((Conduit) event.getClickedBlock().getState()).getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock", BetterBrewingStands.getPlugin(BetterBrewingStands.class))))) return;

        Player player = event.getPlayer();
        Conduit conduit = (Conduit) event.getClickedBlock().getState();
        switch (Objects.requireNonNull(conduit.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock", BetterBrewingStands.getPlugin(BetterBrewingStands.class))), PersistentDataType.STRING))) {
            case "level" -> {
                if (!event.getClickedBlock().isBlockPowered())
                    new LevelGuiOn(player).open();
                else new LevelGuiOff(player).open();
            }
            case "speed" -> {
                if (!event.getClickedBlock().isBlockPowered())
                    new SpeedGuiOn(player).open();
                else new SpeedGuiOff(player).open();
            }
            case "time" -> {
                if (!event.getClickedBlock().isBlockPowered())
                    new TimeGuiOn(player).open();
                else new TimeGuiOff(player).open();
            }
            case "water" -> {
                if (!event.getClickedBlock().isBlockPowered())
                    new WaterGuiOn(player).open();
                else new WaterGuiOff(player).open();
            }
            default -> {
                player.sendMessage("Unknown upgrade block type! Please contact the developer.");
                // Handle unknown upgrade block types if necessary
            }

        }

    }
}
