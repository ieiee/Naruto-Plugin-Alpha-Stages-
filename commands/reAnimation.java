package org.minecraft.minecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.reanimate.reanimation;

import java.sql.SQLException;


public class reAnimation implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player){

            Player player = (Player) commandSender;

            if (strings.length != 1){
                player.sendMessage(ChatColor.RED + "Please use /reanimate <playerName>");
                return true;
            }
            if (reanimation.checkOnlinePlayers(strings[0])){
                player.sendMessage(ChatColor.RED + "The player you have mentioned is not online or not available");
                return true;
            }
            try {

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                if (!reanimationSql.checkSql(strings[0])){
                    player.sendMessage(ChatColor.RED + "Player with name " + strings[0] + " is already alive");
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (reanimation.alreadyIn(strings[0])){
                player.sendMessage(ChatColor.RED + "Someone is already trying to reanimate this person");
                return true;
            }
            reanimation.reList.put(player.getName(), strings[0]);
            player.sendMessage(ChatColor.GREEN + "Done!");
        }

        return true;
    }
}
