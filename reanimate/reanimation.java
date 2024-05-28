package org.minecraft.minecraft.reanimate;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class reanimation{

    public static HashMap<String, String> reList = new HashMap<>();


    public static List<Player> allOnlinePlayers = new ArrayList<>();

    public static boolean checkOnlinePlayers(String name){
        allOnlinePlayers.addAll(Bukkit.getOnlinePlayers());
        for (Player player : allOnlinePlayers){
            String playerName = player.getName();
            if (playerName.equalsIgnoreCase(name)){
                return false;
            }
        }
        return true;
    }

    public static boolean alreadyIn(String name){
        return reList.containsValue(name);
    }

    public static boolean checkCaster(String name){
        return reList.containsKey(name);
    }
}
