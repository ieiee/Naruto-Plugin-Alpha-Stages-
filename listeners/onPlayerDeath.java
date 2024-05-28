package org.minecraft.minecraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.minecraft.minecraft.DatabaseCommands.dbackSql;

import java.sql.SQLException;
import java.util.Objects;

public class onPlayerDeath implements Listener {

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) throws SQLException {
        Player player = event.getEntity().getPlayer();
        double X = event.getEntity().getLocation().getX();
        double Y = event.getEntity().getLocation().getY();
        double Z = event.getEntity().getLocation().getZ();
        String worldName = Objects.requireNonNull(event.getEntity().getLocation().getWorld()).getName();
        assert player != null;
        dbackSql.insert(player, X, Y, Z, worldName);
    }
}
