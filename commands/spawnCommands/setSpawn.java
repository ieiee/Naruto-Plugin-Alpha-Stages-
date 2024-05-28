package org.minecraft.minecraft.commands.spawnCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;
import org.minecraft.minecraft.DatabaseCommands.spawnSql;

import java.sql.SQLException;
import java.util.Objects;

public class setSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){
            try {

                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }

                spawnSql.setSpawn(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), Objects.requireNonNull(player.getLocation().getWorld()).getName(), player);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            commandSender.sendMessage(ChatColor.RED + "This command can only be used by players");
        }

        return true;
    }
}
