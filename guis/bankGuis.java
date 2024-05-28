package org.minecraft.minecraft.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class bankGuis {

    public static Inventory getBankGui(){
        Inventory bankGui = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Bank");
        ItemStack deposit = new ItemStack(Material.CHEST);
        ItemStack withdraw = new ItemStack(Material.DROPPER);
        ItemStack balance = new ItemStack(Material.OAK_SIGN);
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemStack nonBlack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        //deposit
        ItemMeta metaDeposit = deposit.getItemMeta();
        assert metaDeposit != null;
        metaDeposit.setDisplayName(ChatColor.GREEN + "Deposit");
        deposit.setItemMeta(metaDeposit);
        //withdraw
        ItemMeta metaWithdraw = withdraw.getItemMeta();
        assert metaWithdraw != null;
        metaWithdraw.setDisplayName(ChatColor.YELLOW + "Withdraw");
        withdraw.setItemMeta(metaWithdraw);
        //balance
        ItemMeta metaBalance = balance.getItemMeta();
        assert metaBalance != null;
        metaBalance.setDisplayName(ChatColor.GOLD + "Balance");
        balance.setItemMeta(metaBalance);
        //exit
        ItemMeta metaExit = exit.getItemMeta();
        assert metaExit != null;
        metaExit.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(metaExit);
        //non
        ItemMeta metaNonBlack = nonBlack.getItemMeta();
        assert metaNonBlack != null;
        metaNonBlack.setDisplayName(" ");
        nonBlack.setItemMeta(metaNonBlack);

        //set items in gui
        bankGui.setItem(11, deposit);
        bankGui.setItem(13, withdraw);
        bankGui.setItem(15, balance);
        bankGui.setItem(26, exit);
        for (int i = 0; i<= 25; i++){
            if (i == 11 || i == 13 || i == 15){
                continue;
            }
            bankGui.setItem(i, nonBlack);
        }
        return bankGui;
    }

    public static Inventory getWithdrawGui(){
        Inventory withdrawGui = Bukkit.createInventory(null, 27, ChatColor.YELLOW + "" + ChatColor.BOLD + "Withdraw");
        ItemStack full = new ItemStack(Material.DROPPER, 64);
        ItemStack half = new ItemStack(Material.DROPPER, 32);
        ItemStack custom = new ItemStack(Material.OAK_SIGN);
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemStack nonBlack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        //deposit
        ItemMeta metaDeposit = full.getItemMeta();
        assert metaDeposit != null;
        metaDeposit.setDisplayName(ChatColor.GREEN + "All");
        full.setItemMeta(metaDeposit);
        //withdraw
        ItemMeta metaWithdraw = half.getItemMeta();
        assert metaWithdraw != null;
        metaWithdraw.setDisplayName(ChatColor.YELLOW + "Half");
        half.setItemMeta(metaWithdraw);
        //balance
        ItemMeta metaBalance = custom.getItemMeta();
        assert metaBalance != null;
        metaBalance.setDisplayName(ChatColor.GOLD + "Custom");
        custom.setItemMeta(metaBalance);
        //exit
        ItemMeta metaExit = exit.getItemMeta();
        assert metaExit != null;
        metaExit.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(metaExit);
        //non
        ItemMeta metaNonBlack = nonBlack.getItemMeta();
        assert metaNonBlack != null;
        metaNonBlack.setDisplayName(" ");
        nonBlack.setItemMeta(metaNonBlack);

        //set items in gui
        withdrawGui.setItem(11, full);
        withdrawGui.setItem(13, half);
        withdrawGui.setItem(15, custom);
        withdrawGui.setItem(26, exit);
        for (int i = 0; i<= 25; i++){
            if (i == 11 || i == 13 || i == 15){
                continue;
            }
            withdrawGui.setItem(i, nonBlack);
        }
        return withdrawGui;
    }

    public static Inventory getDepositGui(){
        Inventory withdrawGui = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "Deposit");
        ItemStack full = new ItemStack(Material.CHEST, 64);
        ItemStack half = new ItemStack(Material.CHEST, 32);
        ItemStack custom = new ItemStack(Material.OAK_SIGN);
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemStack nonBlack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        //deposit
        ItemMeta metaDeposit = full.getItemMeta();
        assert metaDeposit != null;
        metaDeposit.setDisplayName(ChatColor.GREEN + "All");
        full.setItemMeta(metaDeposit);
        //withdraw
        ItemMeta metaWithdraw = half.getItemMeta();
        assert metaWithdraw != null;
        metaWithdraw.setDisplayName(ChatColor.YELLOW + "Half");
        half.setItemMeta(metaWithdraw);
        //balance
        ItemMeta metaBalance = custom.getItemMeta();
        assert metaBalance != null;
        metaBalance.setDisplayName(ChatColor.GOLD + "Custom");
        custom.setItemMeta(metaBalance);
        //exit
        ItemMeta metaExit = exit.getItemMeta();
        assert metaExit != null;
        metaExit.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(metaExit);
        //non
        ItemMeta metaNonBlack = nonBlack.getItemMeta();
        assert metaNonBlack != null;
        metaNonBlack.setDisplayName(" ");
        nonBlack.setItemMeta(metaNonBlack);

        //set items in gui
        withdrawGui.setItem(11, full);
        withdrawGui.setItem(13, half);
        withdrawGui.setItem(15, custom);
        withdrawGui.setItem(26, exit);
        for (int i = 0; i<= 25; i++){
            if (i == 11 || i == 13 || i == 15){
                continue;
            }
            withdrawGui.setItem(i, nonBlack);
        }
        return withdrawGui;
    }

}
