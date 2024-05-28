package org.minecraft.minecraft.listeners.blockBreak;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.minecraft.minecraft.CustomToolsAndWeapons.tools.*;

public class veinMiner implements Listener {

    public static List<List<Material>> blocks = new ArrayList<>();
    public static List<Material> woodenBlocks = new ArrayList<>();
    public static List<Material> oreBlocks = new ArrayList<>();
    public static List<Material> nonGravityBlocks = new ArrayList<>();

    public static void addBlocks(){

        // wooden blocks here
        woodenBlocks.add(Material.OAK_LOG);
        woodenBlocks.add(Material.SPRUCE_LOG);
        woodenBlocks.add(Material.BIRCH_LOG);
        woodenBlocks.add(Material.ACACIA_LOG);
        woodenBlocks.add(Material.CHERRY_LOG);
        woodenBlocks.add(Material.DARK_OAK_LOG);
        woodenBlocks.add(Material.JUNGLE_LOG);
        woodenBlocks.add(Material.MANGROVE_LOG);
        woodenBlocks.add(Material.COAL_ORE);

        //ore blocks here
        oreBlocks.add(Material.COAL_ORE);
        oreBlocks.add(Material.COPPER_ORE);
        oreBlocks.add(Material.DIAMOND_ORE);
        oreBlocks.add(Material.EMERALD_ORE);
        oreBlocks.add(Material.GOLD_ORE);
        oreBlocks.add(Material.LAPIS_ORE);
        oreBlocks.add(Material.IRON_ORE);
        oreBlocks.add(Material.REDSTONE_ORE);
        oreBlocks.add(Material.DEEPSLATE_COAL_ORE);
        oreBlocks.add(Material.DEEPSLATE_COPPER_ORE);
        oreBlocks.add(Material.DEEPSLATE_DIAMOND_ORE);
        oreBlocks.add(Material.DEEPSLATE_DIAMOND_ORE);
        oreBlocks.add(Material.DEEPSLATE_GOLD_ORE);
        oreBlocks.add(Material.DEEPSLATE_LAPIS_ORE);
        oreBlocks.add(Material.DEEPSLATE_IRON_ORE);
        oreBlocks.add(Material.DEEPSLATE_REDSTONE_ORE);

        //NON Gravity Blocks list
        nonGravityBlocks.add(Material.SAND);
        nonGravityBlocks.add(Material.GRAVEL);

        //adding them to list
        blocks.add(woodenBlocks);
        blocks.add(oreBlocks);
        blocks.add(nonGravityBlocks);
    }

    public static int counter;

    @EventHandler
    public void blockBreak(BlockBreakEvent event){

        String blockName = event.getBlock().getBlockData().getMaterial().name().toLowerCase();
        System.out.println(blockName);
        if (blockName.contains("log")){
            for (Material woodenBlock : blocks.get(0)){
                if (event.getBlock().getBlockData().getMaterial() == woodenBlock){
                    if (Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(Objects.requireNonNull(woodStyle().getItemMeta()).getDisplayName())) {
                        counter = 1;
                        breakWoodenBlocks(event.getBlock());
                        return;
                    }
                }
            }
        } else if (blockName.contains("ore")) {
            for (Material oreBlock : blocks.get(1)){
                if (event.getBlock().getBlockData().getMaterial() == oreBlock){
                    if (Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(Objects.requireNonNull(stoneStyle().getItemMeta()).getDisplayName())) {
                        counter = 1;
                        breakOreBlocks(event.getBlock());
                        return;
                    }
                }
            }
        }
    }

    private void breakWoodenBlocks(Block block) {
        block.breakNaturally();
        if (counter < 32) {
            for (int x = -1; x <= 1; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        Block bCheck = block.getRelative(x, y, z);
                        for (Material i : woodenBlocks){
                            if (bCheck.getBlockData().getMaterial() == i){
                                counter = counter + 1;
                                breakWoodenBlocks(bCheck);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (counter == 32) {
            counter = 0;
        }
    }

    private void breakOreBlocks(Block block) {
        block.breakNaturally();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block bCheck = block.getRelative(x, y, z);
                    for (Material i : oreBlocks){
                        if (bCheck.getBlockData().getMaterial() == i){
                            breakOreBlocks(bCheck);
                            break;
                        }
                    }
                }
            }
        }
        }


}
