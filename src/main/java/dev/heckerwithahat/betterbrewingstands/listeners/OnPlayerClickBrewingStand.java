package dev.heckerwithahat.betterbrewingstands.listeners;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import dev.heckerwithahat.betterbrewingstands.GUIs.BrewingStandGUI;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.Conduit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;


public class OnPlayerClickBrewingStand implements Listener {
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getClickedBlock() == null || event.getClickedBlock().getType() != Material.BREWING_STAND)
            return;
        event.setCancelled(true);
        BrewingStand stand = (BrewingStand) event.getClickedBlock().getState();

        int levelUpgradeCount = 0;
        int timeUpgradeCount = 0;

        for (int x = -2; x <= 2; x++)
            for (int z = -2; z <= 2; z++)
                if ((x == -2 || x == 2 || z == -2 || z == 2) && stand.getWorld().getBlockAt(stand.getLocation().add(x, 0, z)).getState() instanceof Conduit && ((Conduit) stand.getWorld().getBlockAt(stand.getLocation().add(x, 0, z)).getState()).getPersistentDataContainer().has(key("upgradetypeblock")))
                    switch (Objects.requireNonNull(((Conduit) stand.getWorld().getBlockAt(stand.getLocation().add(x, 0, z)).getState()).getPersistentDataContainer().get(Objects.requireNonNull(key("upgradetypeblock")), PersistentDataType.STRING))) {
                        case "level" -> levelUpgradeCount++;
                        case "time" -> timeUpgradeCount++;
                    }


        stand.getPersistentDataContainer().set(key("levelupgradecount"), PersistentDataType.INTEGER, levelUpgradeCount);
        stand.getPersistentDataContainer().set(key("timeupgradecount"), PersistentDataType.INTEGER, timeUpgradeCount);
        stand.update();
        new BrewingStandGUI(event.getPlayer()).open();
    }


    public NamespacedKey key(String id) {
        return NamespacedKey.fromString(id, BetterBrewingStands.getPlugin(BetterBrewingStands.class));
    }

}
