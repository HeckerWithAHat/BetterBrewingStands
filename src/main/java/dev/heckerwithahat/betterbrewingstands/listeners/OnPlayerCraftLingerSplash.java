package dev.heckerwithahat.betterbrewingstands.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class OnPlayerCraftLingerSplash implements Listener {

    @EventHandler
    public void onPlayerCraftLingerSplash(CraftItemEvent event) {
        if (event.getRecipe().getResult().getType() == Material.SPLASH_POTION) {
            for (org.bukkit.inventory.ItemStack item : event.getInventory().getMatrix()) {
                if (item != null && item.getType() == Material.POTION) {
                    // Copy potion meta to the result
                    org.bukkit.inventory.meta.PotionMeta inputMeta = (org.bukkit.inventory.meta.PotionMeta) item.getItemMeta();
                    org.bukkit.inventory.ItemStack result = event.getRecipe().getResult().clone();
                    org.bukkit.inventory.meta.PotionMeta resultMeta = (org.bukkit.inventory.meta.PotionMeta) result.getItemMeta();
                    resultMeta.setBasePotionData(inputMeta.getBasePotionData());
                    resultMeta.clearCustomEffects();
                    for (org.bukkit.potion.PotionEffect effect : inputMeta.getCustomEffects()) {
                        resultMeta.addCustomEffect(effect, true);
                    }
                    result.setItemMeta(resultMeta);
                    event.setCurrentItem(result);
                    break;
                }
            }
        } else if (event.getRecipe().getResult().getType() == Material.LINGERING_POTION) {
            for (org.bukkit.inventory.ItemStack item : event.getInventory().getMatrix()) {
                if (item != null && item.getType() == Material.POTION) {
                    // Copy potion meta to the result
                    org.bukkit.inventory.meta.PotionMeta inputMeta = (org.bukkit.inventory.meta.PotionMeta) item.getItemMeta();
                    org.bukkit.inventory.ItemStack result = event.getRecipe().getResult().clone();
                    org.bukkit.inventory.meta.PotionMeta resultMeta = (org.bukkit.inventory.meta.PotionMeta) result.getItemMeta();
                    resultMeta.setBasePotionData(inputMeta.getBasePotionData());
                    resultMeta.clearCustomEffects();
                    for (org.bukkit.potion.PotionEffect effect : inputMeta.getCustomEffects()) {
                        resultMeta.addCustomEffect(effect, true);
                    }
                    result.setItemMeta(resultMeta);
                    event.setCurrentItem(result);
                    break;
                }
            }
        }
    }

}
