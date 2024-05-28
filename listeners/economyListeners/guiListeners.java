package org.minecraft.minecraft.listeners.economyListeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.minecraft.minecraft.DatabaseCommands.economySql;
import org.minecraft.minecraft.guis.bankGuis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class guiListeners implements Listener {

    public static List<String> withdrawRequests = new ArrayList<>();
    public static List<String> depositRequests = new ArrayList<>();

    @EventHandler
    public static void gui(InventoryClickEvent event) throws SQLException {
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        if (view.getTitle().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Bank")){
            event.setCancelled(true);

            if (event.getSlot() == 15){
                economySql.getBalance(player);
                player.closeInventory();
            } else if (event.getSlot() == 13) {
                player.openInventory(bankGuis.getWithdrawGui());
            } else if (event.getSlot() == 11) {
                player.openInventory(bankGuis.getDepositGui());
            }else if(event.getSlot() == 26){
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public static void withdraw(InventoryClickEvent event) throws SQLException {
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        if (view .getTitle().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Withdraw")){
            event.setCancelled(true);
            if (event.getSlot() == 11) {
                economySql.withdrawAll(player);
                player.closeInventory();
            }else if(event.getSlot() == 13){
                economySql.withdrawHalf(player);
                player.closeInventory();
            }else if(event.getSlot() == 15){
                withdrawRequests.add(player.getName());
                player.sendMessage(ChatColor.GREEN + "Please enter amount in chat you want to withdraw");
                player.closeInventory();
            }else if (event.getSlot() == 26){
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public static void deposit(InventoryClickEvent event) throws SQLException {
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        if (view .getTitle().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Deposit")){
            event.setCancelled(true);
            if (event.getSlot() == 11) {
                economySql.depositAll(player);
                player.closeInventory();
            }else if(event.getSlot() == 13){
                economySql.depositHalf(player);
                player.closeInventory();
            }else if(event.getSlot() == 15){
                depositRequests.add(player.getName());
                player.sendMessage(ChatColor.GREEN + "Please enter the amount you want to deposit in your bank account");
                player.closeInventory();
            }else if (event.getSlot() == 26){
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public static void profileCheck(InventoryClickEvent event){

        InventoryView view = event.getView();

        if (view.getTitle().equalsIgnoreCase(ChatColor.RED + "PlayerProfile")){
            event.setCancelled(true);
        }

    }
}
