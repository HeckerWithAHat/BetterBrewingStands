package dev.heckerwithahat.betterbrewingstands.GUIs;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Collections;

public class TimeGuiOff extends Gui {


    public TimeGuiOff(Player player ) {
        super(player, "time-block-gui-off", "Time Upgrade Block", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(new Icon(Material.BARRIER).setName("Time Upgrade Block is off"), 13);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE).setName(" "), Collections.singleton(13));
    }
}
