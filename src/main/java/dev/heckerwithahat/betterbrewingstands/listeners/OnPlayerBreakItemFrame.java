package dev.heckerwithahat.betterbrewingstands.listeners;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Conduit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class OnPlayerBreakItemFrame implements Listener {

    @EventHandler
    public void onPlayerBreakItemFrame(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof ItemFrame)) return;

        if ((((ItemFrame) e.getEntity()).getItem().getItemMeta().getItemModel().getKey().equals("level") || ((ItemFrame) e.getEntity()).getItem().getItemMeta().getItemModel().getKey().equals("time")))
            e.setCancelled(true);
    }


    @EventHandler
    public void onPlayerBreakItemFrame(HangingBreakEvent e) {

        if (e.getEntity() instanceof org.bukkit.entity.ItemFrame && (((ItemFrame) e.getEntity()).getItem().getItemMeta().getItemModel().getKey().equals("level") || ((ItemFrame) e.getEntity()).getItem().getItemMeta().getItemModel().getKey().equals("time")))
            e.setCancelled(true);
    }

}
