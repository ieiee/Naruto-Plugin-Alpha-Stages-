package org.minecraft.minecraft.commands.economyCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.economySql;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;

import java.sql.SQLException;

public class balance implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){
            try {

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                economySql.getBalance(player);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            commandSender.sendMessage("This command can only be used by players only");
        }
        return true;
    }
}