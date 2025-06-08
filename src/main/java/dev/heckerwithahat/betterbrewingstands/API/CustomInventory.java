package dev.heckerwithahat.betterBrewingStands.API;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomInventory {

    private final Inventory inventory;

    public CustomInventory(String title, int size, InventorySlot... items) {
        inventory = Bukkit.createInventory(null, size, title);
        for (InventorySlot item : items) inventory.setItem(item.slot, item.item);
        for (int i = 0; i < inventory.getSize(); i++)
            if (inventory.getItem(i) == null)
                inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setItem(InventorySlot item) {
        inventory.setItem(item.slot, item.item);
    }

    public void open(org.bukkit.entity.Player player) {
        player.openInventory(inventory);
    }
}
