package dev.heckerwithahat.betterBrewingStands.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnPlayerClickInInventory implements Listener {

    @EventHandler
    public void onPlayerClickInInventory(InventoryClickEvent event) {
        // This method will handle the event when a player clicks in their inventory
        // You can implement your logic here
        // For example, you can check if the clicked item is a specific type and then perform actions accordingly
    }
}
