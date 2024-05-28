package org.minecraft.minecraft.commands.tprCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.DatabaseCommands.tprSql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class tpt implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player player) {
            if (!(args.length == 1)){
                player.sendMessage("Incorrect command, please use /tpt <playerName>");
                return true;
            }
            if (args[0].equalsIgnoreCase(player.getName())){
                player.sendMessage(ChatColor.RED + "Unable to imply to player itself");
                return true;
            }
            if (!check(args[0])){
                player.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            try {

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                tprSql.insert(player, player.getName(), args[0]);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public static boolean check(String receiver){
        List<String> players = new ArrayList<>();
        for (Player i : Bukkit.getOnlinePlayers()){
            players.add(i.getName());
        for (String a : players){
            if (Objects.equals(a, receiver)){
                return true;
            }
        }
        }
        return false;
    }
}
