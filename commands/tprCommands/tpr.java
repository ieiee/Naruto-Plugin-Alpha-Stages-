package org.minecraft.minecraft.commands.tprCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.DatabaseCommands.tprSql;

import java.sql.SQLException;

import static org.minecraft.minecraft.commands.tprCommands.tpt.check;

public class tpr implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player player){
            if (!(args.length == 1)){
                player.sendMessage("Invalid Command, please use /tpr <playerName>");
                return true;
            }
            if (args[0].equalsIgnoreCase(player.getName())){
                player.sendMessage(ChatColor.RED + "Unable to imply to player itself");
                return true;
            }
            if (!check(args[0])) {
                player.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            try {

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                tprSql.tprReject(player, player.getName(), args[0]);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
