package org.minecraft.minecraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffectType;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;

import java.sql.SQLException;

public class onPlayerRespawn implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) throws SQLException {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatColor.GREEN + "You can be bring back to life using reanimation jutsu");
        reanimationSql.addPlayer(player);
        player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 60, 0));
    }

}
