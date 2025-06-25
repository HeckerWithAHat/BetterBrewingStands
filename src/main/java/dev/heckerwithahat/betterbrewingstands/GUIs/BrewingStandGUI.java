package dev.heckerwithahat.betterbrewingstands.GUIs;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.advancedslot.AdvancedSlot;
import mc.obliviate.inventory.advancedslot.AdvancedSlotManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;


public class BrewingStandGUI extends Gui {

    BrewingStand stand;
    int levelUpgradeCount;
    int timeUpgradeCount;
    int speedUpgradeCount;
    int waterUpgradeCount;

    public BrewingStandGUI(Player player) {
        super(player, "better-brewing-stand", "Brewing Stand", 6);
        Set<@NotNull Material> set = new java.util.HashSet<>(Set.of(Material.values()));
        set.remove(Material.BREWING_STAND);
        stand = (BrewingStand) player.getTargetBlock(set, ((int) player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).getDefaultValue())).getState();
        levelUpgradeCount = stand.getPersistentDataContainer().get(key("levelupgradecount"), PersistentDataType.INTEGER);
        timeUpgradeCount = stand.getPersistentDataContainer().get(key("timeupgradecount"), PersistentDataType.INTEGER);
        speedUpgradeCount = stand.getPersistentDataContainer().get(key("speedupgradecount"), PersistentDataType.INTEGER);
        waterUpgradeCount = stand.getPersistentDataContainer().get(key("waterupgradecount"), PersistentDataType.INTEGER);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        AdvancedSlotManager advancedSlotManager = new AdvancedSlotManager(this);

        addItem(new Icon(new ItemStack(Material.WATER_BUCKET, waterUpgradeCount)), 27);
        addItem(new Icon(new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, levelUpgradeCount)), 29);
        addItem(new Icon(new ItemStack(Material.CLOCK, timeUpgradeCount)), 31);
        addItem(new Icon(new ItemStack(Material.REDSTONE, speedUpgradeCount)), 33);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE), List.of(27, 29, 31, 33, 35));

        AdvancedSlot fuelSlot = advancedSlotManager.addAdvancedIcon(35, new Icon(new ItemStack(Material.BARRIER)).setName("Fuel Slot"));
        fuelSlot.onPrePutClick((e, item) -> {
            if (!item.getType().equals(Material.BLAZE_POWDER)) {
                player.sendMessage("You cannot put items here except Blaze Powder!");
                return true;
            }
            return false;
        });

    }




    public NamespacedKey key(String id) {
        return NamespacedKey.fromString(id, BetterBrewingStands.getPlugin(BetterBrewingStands.class));
    }
}
