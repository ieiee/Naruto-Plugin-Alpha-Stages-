package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class skillsSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static void giveWaterBalloonSkill(Player player) throws SQLException {
        PreparedStatement statement58 = connection.prepareStatement("SELECT waterBalloon FROM skills WHERE playerName = ?");
        statement58.setString(1, player.getName());
        ResultSet result58 = statement58.executeQuery();
        if (result58.getString("waterBalloon").equals("Achieved")){
            player.sendMessage(ChatColor.GREEN + "You already have that skill :)");
            statement58.close();
            result58.close();
            return;
        }
        PreparedStatement statement59 = connection.prepareStatement("UPDATE skills SET waterBalloon = 'Achieved' WHERE playerName = ?;");
        statement59.setString(1, player.getName());
        statement59.executeUpdate();
        statement59.close();
        statement58.close();
        statement58.close();
        result58.close();
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Congratulations");
        player.sendMessage(ChatColor.GREEN + "You have your First Step towards rasengan");
    }

    public static void giveRubberBalloonSkill(Player player) throws SQLException {
        PreparedStatement statement60 = connection.prepareStatement("SELECT rubberBalloon FROM skills WHERE playerName = ?");
        statement60.setString(1, player.getName());
        ResultSet result60 = statement60.executeQuery();
        if (result60.getString("rubberBalloon").equals("Achieved")){
            player.sendMessage(ChatColor.GREEN + "You already have that skill :)");
            statement60.close();
            result60.close();
            return;
        }
        PreparedStatement statement61 = connection.prepareStatement("SELECT waterBalloon FROM skills WHERE playerName = ?");
        statement61.setString(1, player.getName());
        ResultSet result61 = statement61.executeQuery();
        if (result61.getString("waterBalloon").equals("notYet")){
            player.sendMessage(ChatColor.DARK_AQUA + "First you need to learn how to deal with WaterBalloon");
            statement61.close();
            result61.close();
            return;
        }
        PreparedStatement statement62 = connection.prepareStatement("UPDATE skills SET rubberBalloon = 'Achieved' WHERE playerName = ?;");
        statement62.setString(1, player.getName());
        statement62.executeUpdate();
        statement62.close();
        statement61.close();
        statement60.close();
        result61.close();
        result60.close();
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Congratulations");
        player.sendMessage(ChatColor.GREEN + "You have your Second Step towards rasengan");
    }

    public static void giveAirBalloonSkill(Player player) throws SQLException {
        PreparedStatement statement63 = connection.prepareStatement("SELECT airBalloon FROM skills WHERE playerName = ?");
        statement63.setString(1, player.getName());
        ResultSet result63 = statement63.executeQuery();
        if (result63.getString("airBalloon").equals("Achieved")){
            player.sendMessage(ChatColor.GREEN + "You already have that skill :)");
            statement63.close();
            result63.close();
            return;
        }
        PreparedStatement statement64 = connection.prepareStatement("SELECT waterBalloon FROM skills WHERE playerName = ?");
        statement64.setString(1, player.getName());
        ResultSet result64 = statement64.executeQuery();
        if (result64.getString("waterBalloon").equals("notYet")){
            player.sendMessage(ChatColor.DARK_AQUA + "First you need to learn how to deal with WaterBalloon");
            statement64.close();
            result64.close();
            return;
        }
        PreparedStatement statement65 = connection.prepareStatement("SELECT rubberBalloon FROM skills WHERE playerName = ?");
        statement65.setString(1, player.getName());
        ResultSet result65 = statement65.executeQuery();
        if (result65.getString("rubberBalloon").equals("notYet")){
            player.sendMessage(ChatColor.DARK_AQUA + "First you need to learn how to deal with RubberBalloon");
            statement65.close();
            result65.close();
            return;
        }
        PreparedStatement statement66 = connection.prepareStatement("UPDATE skills SET airBalloon = 'Achieved' WHERE playerName = ?;");
        statement66.setString(1, player.getName());
        statement66.executeUpdate();
        statement66.close();
        statement63.close();
        statement64.close();
        statement65.close();
        result63.close();
        result64.close();
        result65.close();
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Congratulations");
        player.sendMessage(ChatColor.GREEN + "You have your Third Step towards rasengan");
    }

    public static boolean checkAirBalloon(Player player) throws SQLException {
        PreparedStatement statement67 = connection.prepareStatement("SELECT airBalloon FROM skills WHERE playerName = ?");
        statement67.setString(1, player.getName());
        ResultSet result67 = statement67.executeQuery();
        if (result67.getString("airBalloon").equals("notYet")) {
            statement67.close();
            result67.close();
            return false;
        }
        statement67.close();
        result67.close();
        return true;
    }

    public static boolean checkWaterBalloon(Player player) throws SQLException {
        PreparedStatement statement68 = connection.prepareStatement("SELECT waterBalloon FROM skills WHERE playerName = ?");
        statement68.setString(1, player.getName());
        ResultSet result68 = statement68.executeQuery();
        if (result68.getString("waterBalloon").equals("notYet")) {
            statement68.close();
            result68.close();
            return false;
        }
        statement68.close();
        result68.close();
        return true;
    }

    public static boolean checkRubberBalloon(Player player) throws SQLException {
        PreparedStatement statement69 = connection.prepareStatement("SELECT rubberBalloon FROM skills WHERE playerName = ?");
        statement69.setString(1, player.getName());
        ResultSet result69 = statement69.executeQuery();
        if (result69.getString("rubberBalloon").equals("notYet")) {
            statement69.close();
            result69.close();
            return false;
        }
        statement69.close();
        result69.close();
        return true;
    }
}
