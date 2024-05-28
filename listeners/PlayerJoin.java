package org.minecraft.minecraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;
import org.minecraft.minecraft.DatabaseCommands.loginSql;
import org.minecraft.minecraft.DatabaseCommands.playerDetailSql;
import org.minecraft.minecraft.MyFirstPlugin;
import org.minecraft.minecraft.chakara.chakaraControl;
import org.minecraft.minecraft.discord.BotStart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerJoin implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {

        // when player joins

        if (loginSql.authPlayer(event.getPlayer())){
            event.getPlayer().kickPlayer(ChatColor.GREEN + "This is not an error \n this was for only login checks \n you may now rejoin the server");
            // kicks the new players and asks them to rejoin
        }
        Boolean joinPerm = MyFirstPlugin.getPlugin().getConfig().getBoolean("player_join_title_message");
        String joinMessage = MyFirstPlugin.getJoinMessage().getString("join_message");
        if (joinMessage != null)
            if (joinMessage.contains("%player%"))
                 joinMessage = joinMessage.replace("%player%", event.getPlayer().getName());
        event.setJoinMessage(joinMessage);
        if (joinPerm.equals(true))
            event.getPlayer().sendTitle(ChatColor.BOLD + "" + ChatColor.YELLOW + "Welcome to the server",ChatColor.AQUA + "@" + event.getPlayer().getName() + " have a nice journey ahead");
            // sends title message to the player
        BotStart.playerJoinMessage(event.getPlayer().getName());
        chakaraControl.addOnPlayerJoin(event.getPlayer());

        if (!playerDetailSql.getDetails(event.getPlayer()).next()) {
            playerDetailSql.createProfile(event.getPlayer());
        }

        ResultSet details = playerDetailSql.getDetails(event.getPlayer());
        String clanName = details.getString("clanName");

        Player player = event.getPlayer();

        Team clan = playerDetailSql.getTeam(player, player.getName(), clanName);
        clan.addPlayer(event.getPlayer());

        // sends the player join message to the discord server
    }

}

