package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbackSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static void insert(Player player, double X, double Y, double Z, String worldName) throws SQLException {

        PreparedStatement statement24 = connection.prepareStatement("SELECT * FROM dback WHERE playerName = ?");
        statement24.setString(1, player.getName());
        ResultSet result24 = statement24.executeQuery();
        if (result24.next()){
            PreparedStatement statement25 = connection.prepareStatement("UPDATE dback SET X = ?, Y = ?, Z = ?, worldName =? WHERE playerName = ?;");
            statement25.setDouble(1, X);
            statement25.setDouble(2, Y);
            statement25.setDouble(3, Z);
            statement25.setString(4, worldName);
            statement25.setString(5, player.getName());
            statement25.executeUpdate();
            statement25.close();
            return;
        }

        PreparedStatement statement23 = connection.prepareStatement("INSERT INTO dback (playerName, X, Y, Z, worldName) VALUES (?, ?, ?, ?, ?)");
        statement23.setString(1, player.getName());
        statement23.setDouble(2, X);
        statement23.setDouble(3, Y);
        statement23.setDouble(4, Z);
        statement23.setString(5, worldName);
        statement23.executeUpdate();
        statement23.close();

    }

    public static void dback(Player player) throws SQLException {

        PreparedStatement statement26 = connection.prepareStatement("SELECT * FROM dback WHERE playerName = ?");
        statement26.setString(1, player.getName());
        ResultSet result26 = statement26.executeQuery();
        if (!result26.next()){
            player.sendMessage(ChatColor.RED + "There was no death coordinate was set in recent times");
            return;
        }

        double X = result26.getDouble("X");
        double Y = result26.getDouble("Y");
        double Z = result26.getDouble("Z");
        String worldName = result26.getString("worldName");

        World world = MyFirstPlugin.getWorldByName(worldName);
        Location location = new Location(world, X, Y, Z);
        player.teleport(location);

        statement26.close();
        result26.close();


    }

}
