package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class playerDetailSql {

    public static Connection connection = MyFirstPlugin.getConnection();
    public static HashMap<Integer, String> clans = new HashMap<>();
    public static HashMap<Integer, String> villages = new HashMap<>();

    public static void createVillagesAndClans(){
        villages.put(1, "Leaf");
        villages.put(2, "Sand");
        villages.put(3, "Mist");
        villages.put(4, "Cloud");
        villages.put(5, "Rain");
        villages.put(6, "Stone");

        clans.put(1, "aburame");
        clans.put(2, "akimichi");
        clans.put(3, "hatake");
        clans.put(4, "hyuga");
        clans.put(5, "nara");
        clans.put(6, "sarutobi");
        clans.put(7, "senju");
        clans.put(8, "uchiha");
        clans.put(9, "uzumaki");
        clans.put(10, "yamanaka");
    }

    public static void createProfile(Player player) throws SQLException {

        Random random = new Random();
        Integer villageSelector = random.nextInt(6) + 1;
        Integer clanSelector = random.nextInt(10) + 1;

        PreparedStatement statement79 = connection.prepareStatement("INSERT INTO playerDetails (playerName, villageName, clanName, rank, team, bio, status) VALUES (?, ?, ?, ?, ?, ? ,?)");
        statement79.setString(1, player.getName());
        statement79.setString(2, villages.get(villageSelector));
        statement79.setString(3, clans.get(clanSelector));
        statement79.setString(4, "None");
        statement79.setString(5, "None");
        statement79.setString(6, "None");
        statement79.setString(7,"None");
        statement79.executeUpdate();
        statement79.close();

    }

    public static ResultSet getDetails(Player player) throws SQLException {

        PreparedStatement statement80 = connection.prepareStatement("SELECT * from playerDetails WHERE playerName = ?");
        statement80.setString(1, player.getName());
        return statement80.executeQuery();
    }


    public static Team getTeam(Player player, String name, String clanName){
        Scoreboard s = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        if (s.getTeam(clanName) == null){
            Team t = s.registerNewTeam(clanName);

            switch (clanName){
                case "aburame":
                    t.setPrefix(ChatColor.GREEN + "" + ChatColor.BOLD + "Aburame ");
                    player.setPlayerListName(ChatColor.GREEN + "" + ChatColor.BOLD + "Aburame " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Aburame " + ChatColor.WHITE + name);
                    break;

                case "akimichi":
                    t.setPrefix(ChatColor.YELLOW + "" + ChatColor.BOLD + "Akimichi ");
                    player.setPlayerListName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Akimichi " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Akimichi " + ChatColor.WHITE + name);
                    break;

                case "hatake":
                    t.setPrefix(ChatColor.WHITE + "" + ChatColor.BOLD + "Hatake ");
                    player.setPlayerListName(ChatColor.WHITE + "" + ChatColor.BOLD + "Hatake " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Hatake " + ChatColor.WHITE + name);
                    break;

                case "hyuga":
                    t.setPrefix(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Hyuga ");
                    player.setPlayerListName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Hyuga " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Hyuga " + ChatColor.WHITE + name);
                    break;

                case "nara":
                    t.setPrefix(ChatColor.BLACK + "" + ChatColor.BOLD + "Nara ");
                    player.setPlayerListName(ChatColor.BLACK + "" + ChatColor.BOLD + "Nara " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "Nara " + ChatColor.WHITE + name);
                    break;

                case "sarutobi":
                    t.setPrefix(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sarutobi ");
                    player.setPlayerListName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sarutobi " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sarutobi " + ChatColor.WHITE + name);
                    break;

                case "senju":
                    t.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "Senju ");
                    player.setPlayerListName(ChatColor.AQUA + "" + ChatColor.BOLD + "Senju " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Senju " + ChatColor.WHITE + name);
                    break;

                case "uchiha":
                    t.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "Uchiha ");
                    player.setPlayerListName(ChatColor.RED + "" + ChatColor.BOLD + "Uchiha " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Uchiha " + ChatColor.WHITE + name);
                    break;

                case "uzumaki":
                    t.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Uzumaki ");
                    player.setPlayerListName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Uzumaki " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Uzumaki " + ChatColor.WHITE + name);
                    break;

                case "yamanaka":
                    t.setPrefix(ChatColor.BLUE + "" + ChatColor.BOLD + "Yamanaka ");
                    player.setPlayerListName(ChatColor.BLUE + "" + ChatColor.BOLD + "Yamanaka " + ChatColor.WHITE + name);
                    player.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Yamanaka " + ChatColor.WHITE + name);
                    break;
            }
            return t;
        }

        Team t = s.getTeam(clanName);
        assert t != null;

        switch (clanName) {
            case "aburame":
                t.setPrefix(ChatColor.GREEN + "" + ChatColor.BOLD + "Aburame ");
                player.setPlayerListName(ChatColor.GREEN + "" + ChatColor.BOLD + "Aburame " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Aburame " + ChatColor.WHITE + name);
                break;

            case "akimichi":
                t.setPrefix(ChatColor.YELLOW + "" + ChatColor.BOLD + "Akimichi ");
                player.setPlayerListName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Akimichi " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Akimichi " + ChatColor.WHITE + name);
                break;

            case "hatake":
                t.setPrefix(ChatColor.WHITE + "" + ChatColor.BOLD + "Hatake ");
                player.setPlayerListName(ChatColor.WHITE + "" + ChatColor.BOLD + "Hatake " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Hatake " + ChatColor.WHITE + name);
                break;

            case "hyuga":
                t.setPrefix(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Hyuga ");
                player.setPlayerListName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Hyuga " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Hyuga " + ChatColor.WHITE + name);
                break;

            case "nara":
                t.setPrefix(ChatColor.BLACK + "" + ChatColor.BOLD + "Nara ");
                player.setPlayerListName(ChatColor.BLACK + "" + ChatColor.BOLD + "Nara " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "Nara " + ChatColor.WHITE + name);
                break;

            case "sarutobi":
                t.setPrefix(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sarutobi ");
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sarutobi " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sarutobi " + ChatColor.WHITE + name);
                break;

            case "senju":
                t.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "Senju ");
                player.setPlayerListName(ChatColor.AQUA + "" + ChatColor.BOLD + "Senju " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Senju " + ChatColor.WHITE + name);
                break;

            case "uchiha":
                t.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "Uchiha ");
                player.setPlayerListName(ChatColor.RED + "" + ChatColor.BOLD + "Uchiha " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Uchiha " + ChatColor.WHITE + name);
                break;

            case "uzumaki":
                t.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Uzumaki ");
                player.setPlayerListName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Uzumaki " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Uzumaki " + ChatColor.WHITE + name);
                break;

            case "yamanaka":
                t.setPrefix(ChatColor.BLUE + "" + ChatColor.BOLD + "Yamanaka ");
                player.setPlayerListName(ChatColor.BLUE + "" + ChatColor.BOLD + "Yamanaka " + ChatColor.WHITE + name);
                player.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Yamanaka " + ChatColor.WHITE + name);
                break;
        }

        return s.getTeam(clanName);
    }

}
