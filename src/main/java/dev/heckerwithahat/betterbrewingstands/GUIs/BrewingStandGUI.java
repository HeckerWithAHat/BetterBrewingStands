package dev.heckerwithahat.betterbrewingstands.GUIs;

import mc.obliviate.inventory.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public class BrewingStandGUI extends Gui {


    public BrewingStandGUI(Player player) {
        super(player, "better-brewing-stand", "Brewing Stand", 6);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        fillGui(Material.GRAY_STAINED_GLASS_PANE);

    }
}
