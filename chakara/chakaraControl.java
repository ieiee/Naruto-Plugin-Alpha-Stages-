package org.minecraft.minecraft.chakara;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.minecraft.minecraft.DatabaseCommands.chakaraSql;

import java.sql.SQLException;
import java.util.HashMap;

public class chakaraControl {

    public static HashMap<String, Integer> realTimeChakaraMap = new HashMap<>();
    public static HashMap<String, Integer> maxLevelChakaraMap = new HashMap<>();

    public static void addOnPlayerJoin(Player player) throws SQLException {
        String playerName = player.getName();
        if (realTimeChakaraMap.containsKey(playerName)){
            updateChakaraBar(player);
            return;
        }
        int chakaraLevel = chakaraSql.getChakaraLevel(playerName);
        realTimeChakaraMap.put(playerName, 0);
        maxLevelChakaraMap.put(playerName, chakaraLevel);
        updateChakaraBar(player);
    }

    public static void setOnPlayerLeave(String playerName){
        realTimeChakaraMap.replace(playerName, 0);
    }

    public static void regenerateChakara(Player player){
        String playerName = player.getName();
        int currentChakara = realTimeChakaraMap.get(playerName);
        int maxChakara = maxLevelChakaraMap.get(playerName);
        int newChakara = Math.min(maxChakara, currentChakara + 1);
        realTimeChakaraMap.replace(playerName, newChakara);
        updateChakaraBar(player);
    }

    public static void updateChakaraBar(Player player){
        int chakara = realTimeChakaraMap.get(player.getName());
        String message = ChatColor.AQUA + "Chakara: " + chakara + "/" + maxLevelChakaraMap.get(player.getName());
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public static void reduceChakaraLevel(Player player, Integer amount){
        String playerName = player.getName();
        int currentChakaraLevel = realTimeChakaraMap.get(playerName);
        realTimeChakaraMap.replace(playerName, currentChakaraLevel - amount);
        updateChakaraBar(player);
    }
}
