package org.minecraft.minecraft.CustomToolsAndWeapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class farming {
    public static ItemStack toadBread(){
        ItemStack bread = new ItemStack(Material.BREAD, 1);
        ItemMeta meta = bread.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Toad's Bread");
        meta.setLore(Arrays.asList(ChatColor.AQUA + "A toad's special bread used to get rid of curse marks", ChatColor.YELLOW + "Immunity to Potion Effects", " ", " ", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "PROPERTIES:", ChatColor.WHITE + "Special Toad's Bread is a rare mystical consumable item in the realm", ChatColor.WHITE + "of Minecraft known for its extraordinary benefits upon its consumer.", ChatColor.WHITE + "When eaten, Special Toad's Bread not only satiates hunger but also purges", ChatColor.WHITE + "to potion effects, restoring the player to a state of purity."));
        bread.setItemMeta(meta);
        return bread;
    }

    public static ItemStack enchantedBread(){
        ItemStack heibeiru = new ItemStack(Material.HAY_BLOCK);
        ItemMeta meta = heibeiru.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "Heibeiru");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        meta.setLore(Arrays.asList(ChatColor.AQUA + "Usage:", ChatColor.WHITE + "To create Farmusuutsu"));
        heibeiru.setItemMeta(meta);
        return heibeiru;
    }

    public static ItemStack waterBalloon(){
        ItemStack item = new ItemStack(Material.ALLAY_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, true);
        meta.setDisplayName(ChatColor.YELLOW + "WaterBalloon");
        meta.setLore(Collections.singletonList(ChatColor.WHITE + "Use this to achieve advanced shinobi skills to acquire an 'A' rank jutsu - 'Rasengan'"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack rubberBalloon(){
        ItemStack item = new ItemStack(Material.MOOSHROOM_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, true);
        meta.setDisplayName(ChatColor.YELLOW + "RubberBalloon");
        meta.setLore(Collections.singletonList(ChatColor.WHITE + "Use this to achieve advanced shinobi skills to acquire an 'A' rank jutsu - 'Rasengan'"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack airBalloon(){
        ItemStack item = new ItemStack(Material.EGG);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, true);
        meta.setDisplayName(ChatColor.YELLOW + "AirBalloon");
        meta.setLore(Collections.singletonList(ChatColor.WHITE + "Use this to achieve advanced shinobi skills to acquire an 'A' rank jutsu - 'Rasengan'"));
        item.setItemMeta(meta);
        return item;
    }
}
