package org.minecraft.minecraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.minecraft.minecraft.DatabaseCommands.loginSql;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;

import java.sql.SQLException;

public class playerMovement implements Listener {

    // used to block the movement in the server if player has not logged or registered in the server

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) throws SQLException {

        if (loginSql.getCheck(event.getPlayer()).equalsIgnoreCase("notApproved")) {
            // checks here if the players has logged in
            if (loginSql.getPassword(event.getPlayer()).equals("noPassword")) {
                // checks id the player have to log in or register
                event.getPlayer().sendTitle(ChatColor.BOLD + "" + ChatColor.YELLOW + "Register",ChatColor.AQUA + "@" + event.getPlayer().getName() + ", register using /register <password>");

            }else {
                event.getPlayer().sendTitle(ChatColor.BOLD + "" + ChatColor.YELLOW + "Login",ChatColor.AQUA + "@" + event.getPlayer().getName() + ", login using /login <password>");

            }
            event.getPlayer().addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 60, 0));
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            event.setCancelled(true);
            // finally gives blindness, spectator and cancels the player movement

        }else if (reanimationSql.checkSql(event.getPlayer().getName())){
            event.getPlayer().addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 60, 0));
            event.setCancelled(true);
        }
    }

}
