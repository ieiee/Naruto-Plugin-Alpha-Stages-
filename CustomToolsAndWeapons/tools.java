package org.minecraft.minecraft.CustomToolsAndWeapons;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class tools{

    public static ItemStack woodStyle(){
        ItemStack cAxe = new ItemStack(Material.WOODEN_AXE);
        ItemMeta meta = cAxe.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WoodStyle: " + ChatColor.GREEN +  "Wood Smash Jutsu");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "NarutoSpecial");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        meta.setUnbreakable(true);
        meta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        cAxe.setItemMeta(meta);
        return cAxe;
    }

    public static ItemStack stoneStyle(){
        ItemStack cPickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta meta = cPickaxe.getItemMeta();
        assert meta!= null;
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "EarthStyle: " + ChatColor.GREEN +  "Quarry Smash Jutsu");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "NarutoSpecial");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        meta.setUnbreakable(true);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        cPickaxe.setItemMeta(meta);
        return cPickaxe;
    }

    public static ItemStack rasenshuriken(){
        ItemStack snowball = new ItemStack(Material.SNOWBALL, 1);
        ItemMeta meta = snowball.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WindStyle: " + ChatColor.AQUA +  "Rasenshuriken");
        meta.setLore(Collections.singletonList(ChatColor.GOLD + "NarutoSpecial"));
        meta.setLore(Arrays.asList(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Skills Required:", ChatColor.WHITE + "1. WaterBalloon", ChatColor.WHITE + "2. RubberBalloon", ChatColor.WHITE + "3. AirBalloon", ChatColor.WHITE + "4. WindStyle", ChatColor.GRAY + "!", ChatColor.LIGHT_PURPLE + "Can be acquired by popping balloons with the respective names", ChatColor.GRAY + "!", ChatColor.GRAY + "!", ChatColor.WHITE + "" + ChatColor.BOLD + "Chakara Consumption:", ChatColor.WHITE + "1. 60 For Massive Raseshuriken", ChatColor.WHITE + "2. 20 For Smaller ones"));
        meta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
        snowball.setItemMeta(meta);
        return snowball;
    }

    public static ItemStack fireBall(){
        ItemStack fireball = new ItemStack(Material.FIRE_CHARGE, 1);
        ItemMeta meta = fireball.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "FireBall");
        meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "PROPERTIES:", ChatColor.WHITE + "The FireBall is a powerful jutsu that creates a ball of fire", ChatColor.WHITE + "that can rip things it touches except harder blocks", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Skills Required:", ChatColor.WHITE + "Coming Soon"));
        meta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);
        fireball.setItemMeta(meta);
        return fireball;
    }

    public static ItemStack fireStyle(){
        ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = blaze.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "FireStyle: " + ChatColor.GREEN +  "Fireball Jutsu");
        meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "PROPERTIES:", ChatColor.WHITE + "The FireBall is a powerful jutsu that creates a ball of fire", ChatColor.WHITE + "that can rip things it touches except harder blocks", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Skills Required:", ChatColor.WHITE + "Coming Soon"));
        meta.setLore(Arrays.asList(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Skills Required:", ChatColor.WHITE + "1. FireStyle", ChatColor.GRAY + "!", ChatColor.LIGHT_PURPLE + "Can be acquired by killing blazes", ChatColor.GRAY + "!", ChatColor.GRAY + "!", ChatColor.WHITE + "" + ChatColor.BOLD + "Chakara Consumption:", ChatColor.WHITE + "1. 5 For each attack"));
        meta.addEnchant(Enchantment.ARROW_FIRE, 5, true);
        blaze.setItemMeta(meta);
        return blaze;
    }
}
