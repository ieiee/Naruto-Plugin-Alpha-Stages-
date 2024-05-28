package org.minecraft.minecraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.minecraft.minecraft.DatabaseCommands.loginSql;

import java.sql.SQLException;

public class PlayerKick implements Listener {

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) throws SQLException {
        loginSql.playerAdd(event.getPlayer());
        // registers new player(who is being kicked on first join) to the login table
    }

}
