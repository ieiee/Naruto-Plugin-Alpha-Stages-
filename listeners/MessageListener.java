package org.minecraft.minecraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.minecraft.minecraft.DatabaseCommands.economySql;
import org.minecraft.minecraft.discord.BotStart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.minecraft.minecraft.listeners.economyListeners.guiListeners.withdrawRequests;
import static org.minecraft.minecraft.listeners.economyListeners.guiListeners.depositRequests;

public class MessageListener implements Listener
{

    public static List<String> toRemoveWithdraw = new ArrayList<>();
    public static List<String> toRemoveDeposit = new ArrayList<>();

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) throws SQLException {
        // used to send message received from the minecraft server to the discord server

        for (String playerName : withdrawRequests){
            if (event.getPlayer().getName().equalsIgnoreCase(playerName)){
                if (event.getMessage().matches("\\d+")) {
                    double amount = Double.parseDouble(event.getMessage());
                    economySql.withdraw(event.getPlayer(), amount);
                    toRemoveWithdraw.add(playerName);
                }else{
                    event.getPlayer().sendMessage(ChatColor.RED + "Please enter only Integer values");
                    String message = event.getMessage();
                    String player = event.getPlayer().getName();
                    BotStart.send(player, message);
                }
            }
        }
        withdrawRequests.removeAll(toRemoveWithdraw);

        for (String playerName : depositRequests){
            if (event.getPlayer().getName().equalsIgnoreCase(playerName)){
                if (event.getMessage().matches("\\d+")){
                    double amount = Double.parseDouble(event.getMessage());
                    economySql.deposit(event.getPlayer(), amount);
                    toRemoveDeposit.add(playerName);
                }else{
                    event.getPlayer().sendMessage(ChatColor.RED + "Please enter only Integer values");
                }
            }
        }
        depositRequests.removeAll(toRemoveDeposit);

        String message = event.getMessage();
        String player = event.getPlayer().getName();
        BotStart.send(player, message);

    }
}
