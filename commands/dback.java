package org.minecraft.minecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.dbackSql;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;

import java.sql.SQLException;

public class dback implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){

            try {

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                dbackSql.dback(player);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return true;
    }
}
