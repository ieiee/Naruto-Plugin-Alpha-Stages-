package org.minecraft.minecraft.commands.economyCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.DatabaseCommands.reanimationSql;

import java.sql.SQLException;

import static org.minecraft.minecraft.guis.bankGuis.getBankGui;

public class bank implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){

            try {
                if (reanimationSql.checkSql(player.getName())){
                    player.sendMessage(ChatColor.RED + "You are not alive, so cannot use commands");
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            commandSender.sendMessage("Bank");
            Inventory gui = getBankGui();
            ((Player) commandSender).openInventory(gui);

        }else {
            commandSender.sendMessage("This command can only be used by players only");
        }
        return true;
    }
}
