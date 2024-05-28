package org.minecraft.minecraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.minecraft.minecraft.DatabaseCommands.loginSql;
import org.minecraft.minecraft.MyFirstPlugin;
import org.minecraft.minecraft.chakara.chakaraControl;
import org.minecraft.minecraft.discord.BotStart;

import java.sql.SQLException;

public class PlayerLeave implements Listener
{

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws SQLException {

        // when a player leaved

        String leaveMessage = MyFirstPlugin.getLeaveMessage().getString("leave_message");

        // gets join leave message from the leave_message.config

        if (leaveMessage != null)
            if (leaveMessage.contains("%player%"))
                leaveMessage = leaveMessage.replace("%player%", event.getPlayer().getName());
        event.setQuitMessage(leaveMessage);
        BotStart.playerLeaveMessage(event.getPlayer().getName());
        // sends player leave message to the discord server
        loginSql.setNo(event.getPlayer());
        // sets notApproved in the loggedIn column
        chakaraControl.setOnPlayerLeave(event.getPlayer().getName());
    }
}
