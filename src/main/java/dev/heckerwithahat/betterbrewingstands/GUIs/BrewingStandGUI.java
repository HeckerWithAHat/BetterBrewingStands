package dev.heckerwithahat.betterbrewingstands.GUIs;

import dev.heckerwithahat.betterbrewingstands.BetterBrewingStands;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.advancedslot.AdvancedSlot;
import mc.obliviate.inventory.advancedslot.AdvancedSlotManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;


public class BrewingStandGUI extends Gui {

    BrewingStand stand;
    int levelUpgradeCount;
    int timeUpgradeCount;


    public BrewingStandGUI(Player player) {
        super(player, "better-brewing-stand", "Brewing Stand", 6);
        Set<@NotNull Material> set = new java.util.HashSet<>(Set.of(Material.values()));
        set.remove(Material.BREWING_STAND);
        stand = (BrewingStand) player.getTargetBlock(set, ((int) player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).getDefaultValue())).getState();
        levelUpgradeCount = stand.getPersistentDataContainer().get(key("levelupgradecount"), PersistentDataType.INTEGER);
        timeUpgradeCount = stand.getPersistentDataContainer().get(key("timeupgradecount"), PersistentDataType.INTEGER);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        AdvancedSlotManager advancedSlotManager = new AdvancedSlotManager(this);
        fillGui(new Icon(Material.GRAY_STAINED_GLASS_PANE).setName(" "), List.of(10, 12, 14, 16, 19, 21, 23, 28, 30, 32, 34, 48, 50));

        for (int i = 0; i < 3; i++) {
            AdvancedSlot fuelSlot = advancedSlotManager.addAdvancedIcon(10 + (2 * i), new Icon(Material.BARRIER).setName("Missing Blaze Powder"));
            fuelSlot.onPrePutClick((e, item) -> {
                if (!item.getType().equals(Material.BLAZE_POWDER)) {
                    player.sendMessage("You cannot put items here except Blaze Powder!");
                    return true;
                }
                return false;
            });
            AdvancedSlot wartSlot = advancedSlotManager.addAdvancedIcon(19 + (2 * i), new Icon(Material.BARRIER).setName("Missing Nether Wart"));
            wartSlot.onPrePutClick((e, item) -> {
                if (!item.getType().equals(Material.NETHER_WART)) {
                    player.sendMessage("You cannot put items here except Nether Wart!");
                    return true;
                }
                return false;
            });
            AdvancedSlot catalystSlot = advancedSlotManager.addAdvancedIcon(28 + (2 * i), new Icon(Material.BARRIER).setName("Missing Effect Catalyst"));

        }
        addItem(new Icon(new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, levelUpgradeCount)).setName("Potion Level"), 16);
        addItem(new Icon(new ItemStack(Material.CLOCK, timeUpgradeCount)).setName("Time Level"), 34);


        addItem(new Icon(Material.BREWING_STAND).setName("Click to Brew Potion").onClick(e -> brew()), 48);
        AdvancedSlot bottleInput1 = advancedSlotManager.addAdvancedIcon(50, new Icon(Material.BARRIER).setName("Input Potion Bottle"));
        bottleInput1.onPrePutClick((e, item) -> {
            if (item.getType() != Material.POTION && item.getType() != Material.SPLASH_POTION && item.getType() != Material.LINGERING_POTION) {
                player.sendMessage("You can only put Potions here!");
                return true;
            }
            return false;
        });

        AdvancedSlot bottleInput2 = advancedSlotManager.addAdvancedIcon(51, new Icon(Material.BARRIER).setName("Input Potion Bottle"));
        bottleInput2.onPrePutClick((e, item) -> {
            if (item.getType() != Material.POTION && item.getType() != Material.SPLASH_POTION && item.getType() != Material.LINGERING_POTION) {
                player.sendMessage("You can only put Potions here!");
                return true;
            }
            return false;
        });

        AdvancedSlot bottleInput3 = advancedSlotManager.addAdvancedIcon(52, new Icon(Material.BARRIER).setName("Input Potion Bottle"));
        bottleInput3.onPrePutClick((e, item) -> {
            if (item.getType() != Material.POTION && item.getType() != Material.SPLASH_POTION && item.getType() != Material.LINGERING_POTION) {
                player.sendMessage("You can only put Potions here!");
                return true;
            }
            return false;
        });
    }


    public NamespacedKey key(String id) {
        return NamespacedKey.fromString(id, BetterBrewingStands.getPlugin(BetterBrewingStands.class));
    }

    public void brew() {

        // Iterate through each set of slots (fuel, wart, catalyst)
        for (int i = 0; i < 5; i++) {
            ItemStack fuel = getInventory().getItem(10 + i);
            ItemStack wart = getInventory().getItem(19 + i);
            ItemStack catalyst = getInventory().getItem(28 + i);
            ItemStack bottle1 = getInventory().getItem(50);
            ItemStack bottle2 = getInventory().getItem(51);
            ItemStack bottle3 = getInventory().getItem(52);

            if (fuel != null && fuel.getType() == Material.BLAZE_POWDER && fuel.getAmount() >= (levelUpgradeCount) &&
                    wart != null && wart.getType() == Material.NETHER_WART &&
                    catalyst != null && catalyst.getType() != Material.BARRIER &&
                    ((bottle1 != null && bottle1.getType() != Material.BARRIER) || (bottle2 != null && bottle2.getType() != Material.BARRIER) || (bottle3 != null && bottle3.getType() != Material.BARRIER))) {
                if (bottle1 != null && (bottle1.getType() == Material.POTION || bottle1.getType() == Material.SPLASH_POTION || bottle1.getType() == Material.LINGERING_POTION)) {
                    // Example: Use catalyst type to determine effect, here using SPEED as a placeholder
                    PotionEffect effect = new PotionEffect(
                            Objects.requireNonNull(PotionEffectType.getByName(Objects.requireNonNull(BetterBrewingStands.getPlugin(BetterBrewingStands.class).getConfig().getString(String.valueOf(catalyst.getType()))))),
                            (int) (20 * 60 * timeUpgradeCount), // 30 sec base
                            levelUpgradeCount - 1 // PotionEffect levels are 0-based
                    );
                    PotionMeta meta = (PotionMeta) bottle1.getItemMeta();
                    meta.addCustomEffect(effect, true);
                    bottle1.setItemMeta(meta);
                }
                if (bottle2 != null && (bottle2.getType() == Material.POTION || bottle2.getType() == Material.SPLASH_POTION || bottle2.getType() == Material.LINGERING_POTION)) {
                    // Example: Use catalyst type to determine effect, here using SPEED as a placeholder
                    PotionEffect effect = new PotionEffect(
                            Objects.requireNonNull(PotionEffectType.getByName(Objects.requireNonNull(BetterBrewingStands.getPlugin(BetterBrewingStands.class).getConfig().getString(String.valueOf(catalyst.getType()))))),
                            (int) (20 * 60 * timeUpgradeCount), // 30 sec base
                            levelUpgradeCount - 1 // PotionEffect levels are 0-based
                    );
                    PotionMeta meta = (PotionMeta) bottle2.getItemMeta();
                    meta.addCustomEffect(effect, true);
                    bottle2.setItemMeta(meta);
                }
                if (bottle3 != null && (bottle3.getType() == Material.POTION || bottle3.getType() == Material.SPLASH_POTION || bottle3.getType() == Material.LINGERING_POTION)) {
                    // Example: Use catalyst type to determine effect, here using SPEED as a placeholder
                    PotionEffect effect = new PotionEffect(
                            Objects.requireNonNull(PotionEffectType.getByName(Objects.requireNonNull(BetterBrewingStands.getPlugin(BetterBrewingStands.class).getConfig().getString(String.valueOf(catalyst.getType()))))),
                            (int) (20 * 60 * timeUpgradeCount), // 30 sec base
                            levelUpgradeCount - 1 // PotionEffect levels are 0-based
                    );
                    PotionMeta meta = (PotionMeta) bottle3.getItemMeta();
                    meta.addCustomEffect(effect, true);
                    bottle3.setItemMeta(meta);
                }
                fuel.setAmount(fuel.getAmount() - levelUpgradeCount);
                wart.setAmount(wart.getAmount() - 1);
                catalyst.setAmount(catalyst.getAmount() - 1);
            }
        }

    }

}
