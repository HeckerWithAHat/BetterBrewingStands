package dev.heckerwithahat.betterbrewingstands.GUIs;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Collections;

public class WaterGuiOn extends Gui {


    public WaterGuiOn(Player player ) {
        super(player, "water-block-gui-on", "Water Upgrade Block", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(new Icon(Material.MAGENTA_GLAZED_TERRACOTTA).setName("Infinite water is being supplied"), 13);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE), Collections.singleton(13));
    }
}
