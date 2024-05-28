package org.minecraft.minecraft.commands.warpCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.DatabaseCommands.warpSql;

import java.sql.SQLException;
import java.util.Objects;

public class setWarp implements CommandExecutor {

    // used by players to set a warp location in server

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player){
            if (args.length == 1) {
                try {

                    if (reanimationSql.checkSql(player.getName())){
                        player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                        return true;
                    }

                    warpSql.setWarp(args[0], player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), Objects.requireNonNull(player.getLocation().getWorld()).getName(), player);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{
                player.sendMessage("This command can only encounter with 1 argument");
            }
        }
        else{
            sender.sendMessage("This command can only be used by players only");
        }
        return true;
    }
}
