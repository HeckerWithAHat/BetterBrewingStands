package dev.heckerwithahat.betterbrewingstands.GUIs;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Collections;

public class LevelGuiOff extends Gui {


    public LevelGuiOff(Player player ) {
        super(player, "level-block-gui-off", "Level Upgrade Block", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(new Icon(Material.BARRIER).setName("Level Upgrade Block is Off"), 13);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE), Collections.singleton(13));
    }
}
