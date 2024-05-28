package org.minecraft.minecraft.commands.loginCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.loginSql;

import java.sql.SQLException;

public class login implements CommandExecutor {

    // used by players to login in the server

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length != 1) {

            sender.sendMessage(ChatColor.RED + "You didn't used command correctly");
            sender.sendMessage(ChatColor.GREEN + "Use /login <password>");
            return true;
        }
        //
        //
        //
        if (sender instanceof Player) {

            Player player = (Player) sender;
            try {
                loginSql.login(player, args[0]);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else{
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players only");
        }
        //
        //
        //
        return true;
    }
}
