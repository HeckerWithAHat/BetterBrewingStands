package dev.heckerwithahat.betterbrewingstands.listeners;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Conduit;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;


public class OnPlayerPlaceCustomBlock implements Listener {
    @EventHandler
    public void onPlayerPlaceCustomBlock(BlockPlaceEvent event) {
        // This method will handle the event when a player places a custom block
        if (Objects.requireNonNull(event.getItemInHand().getItemMeta()).getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeitem", BetterBrewingStands.getPlugin(BetterBrewingStands.class))))) {
            event.getBlockPlaced().setType(Material.CONDUIT);
            Conduit block = (Conduit) event.getBlockPlaced().getWorld().getBlockAt(event.getBlockPlaced().getLocation()).getState();
            String type = event.getItemInHand().getItemMeta().getPersistentDataContainer().get(
                    Objects.requireNonNull(NamespacedKey.fromString("upgradetypeitem",
                            BetterBrewingStands.getPlugin(BetterBrewingStands.class))),
                    PersistentDataType.STRING);
            block.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeblock",
                            BetterBrewingStands.getPlugin(BetterBrewingStands.class))),
                    org.bukkit.persistence.PersistentDataType.STRING,
                    Objects.requireNonNull(type));
            Waterlogged w = ((Waterlogged) block.getBlockData());
            w.setWaterlogged(false);
            block.setBlockData(w);
            block.update();

            ItemFrame frame = (ItemFrame) event.getPlayer().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.ITEM_FRAME);
            frame.setVisible(false);
            frame.setFacingDirection(event.getBlockAgainst().getFace(event.getBlock()), true);
            ItemStack item = new ItemStack(Material.DIRT);
            ItemMeta meta = item.getItemMeta();
            if (type.equals("level")) {
                meta.setItemModel(key("level"));
            } else if (type.equals("time")) {
                meta.setItemModel(key("time"));
            }
            item.setItemMeta(meta);
            frame.setItem(item);


        }
    }

    public NamespacedKey key(String id) {
        return NamespacedKey.fromString(id, BetterBrewingStands.getPlugin(BetterBrewingStands.class));
    }
}
