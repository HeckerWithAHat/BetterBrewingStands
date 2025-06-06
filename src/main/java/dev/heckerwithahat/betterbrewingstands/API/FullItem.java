package dev.heckerwithahat.betterBrewingStands.API;

import dev.heckerwithahat.betterBrewingStands.BetterBrewingStands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class FullItem extends ItemStack {

    public FullItem(Material material, int count, String name, String[] lore, boolean unbreakable) {
        super(material, count);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(name);
        meta.setUnbreakable(unbreakable);
        meta.setLore(java.util.Arrays.asList(lore));
        meta.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("upgradetypeitem", BetterBrewingStands.getPlugin(BetterBrewingStands.class))), PersistentDataType.STRING, "level");
        this.setItemMeta(meta);
    }
}
