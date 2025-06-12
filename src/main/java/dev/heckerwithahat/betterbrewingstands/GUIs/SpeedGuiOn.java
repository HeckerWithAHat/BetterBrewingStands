package dev.heckerwithahat.betterbrewingstands.GUIs;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Collections;

public class SpeedGuiOn extends Gui {


    public SpeedGuiOn(Player player ) {
        super(player, "speed-block-gui-on", "Speed Upgrade Block", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(new Icon(Material.MAGENTA_GLAZED_TERRACOTTA).setName("-20% brewing time to all Potions"), 13);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE), Collections.singleton(13));
    }
}
