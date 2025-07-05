package dev.heckerwithahat.betterbrewingstands.listeners;

import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnPlayerRotateItemFrame implements Listener {

    @EventHandler
    public void onPlayerRotateItemFrame(org.bukkit.event.player.PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof org.bukkit.entity.ItemFrame && (((ItemFrame) event.getRightClicked()).getItem().getItemMeta().getItemModel().getKey().equals("level") || ((ItemFrame) event.getRightClicked()).getItem().getItemMeta().getItemModel().getKey().equals("time"))) {
            event.setCancelled(true);

        }
    }

}
