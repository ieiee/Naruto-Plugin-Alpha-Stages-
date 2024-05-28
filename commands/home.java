package org.minecraft.minecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;

import java.sql.SQLException;
import java.util.Objects;

public class home implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String labels, @NotNull String[] args) {

        if (sender instanceof Player player){

            try {
                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (player.getBedSpawnLocation() == null){
                player.sendMessage(ChatColor.RED + "Please set a bed spawn location");
                return true;
            }
            player.teleport(Objects.requireNonNull(player.getBedSpawnLocation()));
        }else{
            sender.sendMessage("This command can only be used by players only");
        }

        return true;
    }
}
