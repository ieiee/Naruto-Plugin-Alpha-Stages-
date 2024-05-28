package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class warpSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static void setWarp(String Name, Double X, Double Y, Double Z, String worldName, Player player) throws SQLException {

        PreparedStatement statement15 = connection.prepareStatement("SELECT * from warp WHERE name = ?");
        statement15.setString(1, Name);
        ResultSet result15 = statement15.executeQuery();
        if (result15.next()){
            player.sendMessage(ChatColor.RED + "A warp with this name already exists");
            return;
        }

        PreparedStatement statement = connection.prepareStatement("INSERT INTO warp (name, X, Y, Z, worldName) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, Name);
        statement.setDouble(2, X);
        statement.setDouble(3, Y);
        statement.setDouble(4, Z);
        statement.setString(5, worldName);
        statement.executeUpdate();
        statement.close();
        player.sendMessage(ChatColor.GREEN + "Warp named " + Name + " was successfully created");

    }

    public static void warp(String name, Player player) throws SQLException {

        PreparedStatement statement12 = connection.prepareStatement("SELECT X, Y, Z, worldName FROM warp WHERE name = ?");
        statement12.setString(1, name);
        ResultSet result12 = statement12.executeQuery();
        if (!result12.next()){
            player.sendMessage(ChatColor.RED + "The warp with name " + name + " does not exists");
            return;
        }
        double X = result12.getDouble("X");
        double Y = result12.getDouble("Y");
        double Z = result12.getDouble("Z");
        String worldName = result12.getString("worldName");
        Location location = new Location(MyFirstPlugin.getWorldByName(worldName), X, Y, Z);
        player.teleport(location);

    }

    public static void delWarp(String name, Player player) throws SQLException {

        PreparedStatement statement13 = connection.prepareStatement("SELECT * FROM warp WHERE name = ?");
        statement13.setString(1, name);
        ResultSet result13 = statement13.executeQuery();
        if (!result13.next()){
            player.sendMessage(ChatColor.RED + "The warp with name " + name + " does not exists");
            return;
        }
        PreparedStatement statement14 = connection.prepareStatement("DELETE FROM warp where name = ?");
        statement14.setString(1, name);
        statement14.executeUpdate();
        player.sendMessage(ChatColor.GREEN + "Warp named " + name + " was successfully deleted");

    }

}
