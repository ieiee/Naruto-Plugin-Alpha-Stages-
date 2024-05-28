package org.minecraft.minecraft.listeners.reanimationJutsu;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.reanimate.reanimation;

import java.sql.SQLException;


public class reanimationZombie implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) throws SQLException {

        if (event.getEntityType() == EntityType.ZOMBIE){
            Location location = event.getEntity().getLocation();
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    if (reanimation.checkCaster(player.getName())){
                        if (reanimation.checkOnlinePlayers(reanimation.reList.get(player.getName()))){
                            player.sendMessage("The player is either not online or not available");
                            return;
                        }
                        player.getWorld().spawnParticle(Particle.ASH, event.getEntity().getLocation(), 50, 0.5, 0.5, 0.5, 0.1);
                        player.getWorld().playSound(event.getEntity().getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 1);
                        Player deadPlayer = Bukkit.getPlayer(reanimation.reList.get(player.getName()));
                        assert deadPlayer != null;
                        deadPlayer.teleport(location);
                        reanimationSql.removePlayer(deadPlayer);
                        reanimation.reList.remove(player.getName(), deadPlayer.getName());
                        deadPlayer.setGameMode(GameMode.SURVIVAL);
                    }
                    event.getDrops().clear();
                }
        }
    }
}
