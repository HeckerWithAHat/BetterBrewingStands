package dev.heckerwithahat.betterbrewingstands.GUIs;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Collections;

public class WaterGuiOff extends Gui {


    public WaterGuiOff(Player player ) {
        super(player, "water-block-gui-off", "Water Upgrade Block", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(new Icon(Material.BARRIER).setName("Water Upgrade Block is off"), 13);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE), Collections.singleton(13));
    }
}
