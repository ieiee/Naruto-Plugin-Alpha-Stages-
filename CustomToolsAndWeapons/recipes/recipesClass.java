package org.minecraft.minecraft.CustomToolsAndWeapons.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.minecraft.minecraft.MyFirstPlugin;

import static org.minecraft.minecraft.CustomToolsAndWeapons.farming.*;
import static org.minecraft.minecraft.CustomToolsAndWeapons.tools.*;

public class recipesClass{

    public static MyFirstPlugin plugin = MyFirstPlugin.getPlugin();

    public static void cAxeRecipe(){
        ItemStack cAxe = woodStyle();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "WoodStyle"), cAxe);
        recipe.shape("WWW", "WSW", " S ");
        recipe.setIngredient('W', Material.OAK_PLANKS);
        recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(recipe);
    }

    public static void cPickaxeRecipe(){
        ItemStack cPickaxe = stoneStyle();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "StoneStyle"), cPickaxe);
        recipe.shape("SSS", "STS", " T ");
        recipe.setIngredient('S', Material.STONE);
        recipe.setIngredient('T', Material.STICK);
        plugin.getServer().addRecipe(recipe);
    }

    public static void rasenshurikens(){
        ItemStack fireball = rasenshuriken();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "Rasengan"), fireball);
        recipe.shape("FG ", "   ", "   ");
        recipe.setIngredient('F', Material.FIREWORK_STAR);
        recipe.setIngredient('G', Material.FIRE_CHARGE);
        plugin.getServer().addRecipe(recipe);
    }

    public static void fireballs(){
        ItemStack fireball = fireBall();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "Fireball"), fireball);
        recipe.shape("FFF", "FFF", "FFF");
        recipe.setIngredient('F', Material.FIREWORK_STAR);
        plugin.getServer().addRecipe(recipe);
    }

    public static void fireStyles(){
        ItemStack fireball = fireStyle();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "FireStyle"), fireball);
        recipe.shape("FB ", "   ", "   ");
        recipe.setIngredient('F', Material.FIRE_CHARGE);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        plugin.getServer().addRecipe(recipe);
    }

    public static void toadsBread(){
        ItemStack bread = toadBread();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "ToadsBread"), bread);
        recipe.shape("WWW", "WSW", "WWW");
        recipe.setIngredient('W', Material.BREAD);
        recipe.setIngredient('S', Material.LILY_PAD);
        plugin.getServer().addRecipe(recipe);
    }

    public static void heibeirus(){
        ItemStack item = enchantedBread();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "Heibeiru"), item);
        recipe.shape("HHH", "HHH", "HHH");
        recipe.setIngredient('H', Material.HAY_BLOCK);
        plugin.getServer().addRecipe(recipe);
    }

    public static void wBalloons(){
        ItemStack item = waterBalloon();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "WaterBalloon"), item);
        recipe.shape("SSS", "SWS", "SSS");
        recipe.setIngredient('S', Material.SLIME_BALL);
        recipe.setIngredient('W', Material.WATER_BUCKET);
        plugin.getServer().addRecipe(recipe);
    }

    public static void rBalloons(){
        ItemStack item = rubberBalloon();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "RubberBalloon"), item);
        recipe.shape("SSS", "SSS", "SSS");
        recipe.setIngredient('S', Material.SLIME_BALL);
        plugin.getServer().addRecipe(recipe);
    }

    public static void aBalloons(){
        ItemStack item = airBalloon();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "AirBalloon"), item);
        recipe.shape("SSS", "S S", "SSS");
        recipe.setIngredient('S', Material.SLIME_BALL);
        plugin.getServer().addRecipe(recipe);
    }
}
