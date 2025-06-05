package dev.heckerwithahat.betterBrewingStands.API;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FullItem extends ItemStack {
    public FullItem(Material material, int count, String name, String[] lore, boolean unbreakable, int cmd) {
        super(material, count);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(name);
        meta.setUnbreakable(unbreakable);
        meta.setLore(java.util.Arrays.asList(lore));
        meta.setCustomModelData(cmd);
        this.setItemMeta(meta);
    }
    public FullItem(Material material, int count, String name, String[] lore, boolean unbreakable) {
        super(material, count);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(name);
        meta.setUnbreakable(unbreakable);
        meta.setLore(java.util.Arrays.asList(lore));
        this.setItemMeta(meta);
    }
}
