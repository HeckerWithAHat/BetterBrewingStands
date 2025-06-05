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

import java.util.Objects;

public class OnPlayerClickCustomBlock implements Listener {

    @EventHandler
    public void onPlayerClickCustomBlock(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && block.getType().equals(Material.CONDUIT)) {
            Conduit conduit = (Conduit) block;
            player.sendMessage("You clicked on a conduit!");
            if (conduit.getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("upgradetype", BetterBrewingStands.getPlugin(BetterBrewingStands.class))))) {
                player.sendMessage("This block is a BBS upgrade block!");
            }
        }
    }
}
