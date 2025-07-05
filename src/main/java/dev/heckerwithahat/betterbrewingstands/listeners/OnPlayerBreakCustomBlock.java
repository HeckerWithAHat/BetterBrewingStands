package dev.heckerwithahat.betterbrewingstands.listeners;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Conduit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

import static dev.heckerwithahat.betterbrewingstands.BetterBrewingStands.LEVEL_BLOCK;
import static dev.heckerwithahat.betterbrewingstands.BetterBrewingStands.TIME_BLOCK;

public class OnPlayerBreakCustomBlock implements Listener {

    @EventHandler
    public void onPlayerBreakCustomBlock(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.CONDUIT) && ((Conduit) event.getBlock().getState()).getPersistentDataContainer().has(key("upgradetypeblock"))) {
            String upgradeType = Objects.requireNonNull(((Conduit) event.getBlock().getState()).getPersistentDataContainer().get(key("upgradetypeblock"), org.bukkit.persistence.PersistentDataType.STRING));
            switch (upgradeType) {
                case "level" -> {
                    event.setDropItems(false);
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), LEVEL_BLOCK);

                }
                case "time" -> {
                    event.setDropItems(false);
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), TIME_BLOCK);
                }
            }
            event.getBlock().getLocation().getWorld().getNearbyEntities(event.getBlock().getLocation().add(0.5, 0.5, 0.5), 0.5, 0.5, 0.5).forEach(entity -> {
                if (entity instanceof ItemFrame)
                    entity.remove();



            });
        }
    }

    public NamespacedKey key(String id) {
        return NamespacedKey.fromString(id, BetterBrewingStands.getPlugin(BetterBrewingStands.class));
    }

}
