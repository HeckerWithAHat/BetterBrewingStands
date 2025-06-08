package dev.heckerwithahat.betterBrewingStands.API;

import dev.heckerwithahat.betterBrewingStands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class InventorySlot {
    public final int slot;
    public final CustomItem item;
    public final InventorySlotType type;

    public InventorySlot(int slot, CustomItem item, InventorySlotType type) {
        this.slot = slot;
        this.item = item;
        this.type = type;
    }

    public InventorySlot(int slot, Material item, InventorySlotType type) {
        this.slot = slot;
        this.item = new CustomItem(item);
        Objects.requireNonNull(this.item.getItemMeta()).getPersistentDataContainer().set(
                Objects.requireNonNull(NamespacedKey.fromString("custominventoryslottype",
                        BetterBrewingStands.getPlugin(BetterBrewingStands.class))), PersistentDataType.STRING,
                type.name());
        this.type = type;
    }
}

