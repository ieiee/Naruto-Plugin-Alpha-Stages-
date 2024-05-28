package org.minecraft.minecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.playerDetailSql;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.guis.playerDetailGuis;

import java.sql.SQLException;

public class profile implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){

            if (strings.length != 1){
                player.sendMessage(ChatColor.RED + "Please use command correctly /profile <playerName>");
                return true;
            }

            try {

                if (!playerDetailSql.getDetails(player).next()){
                    player.sendMessage(ChatColor.RED + "The player name you have entered is not found");
                    return true;
                }

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                player.openInventory(playerDetailGuis.getPlayerDetails(strings[0]));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            commandSender.sendMessage(ChatColor.RED + "This command can only be used by players");
        }

        return true;
    }
}
