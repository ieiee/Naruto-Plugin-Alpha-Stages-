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

public class spawnSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static void setSpawn(Double X, Double Y, Double Z, String worldName, Player player) throws SQLException {


        PreparedStatement statement19 = connection.prepareStatement("SELECT * FROM spawn WHERE name = 'spawn'");
        ResultSet result19 = statement19.executeQuery();
        if (result19.next()){
            PreparedStatement statement20 = connection.prepareStatement("UPDATE spawn SET X = ?, Y = ?, Z = ?, worldName = ?;");
            statement20.setDouble(1, X);
            statement20.setDouble(2, Y);
            statement20.setDouble(3, Z);
            statement20.setString(4, worldName);
            statement20.executeUpdate();
            player.sendMessage(ChatColor.GREEN + "Spawn set successfully");
            return;
        }

        PreparedStatement statement17 = connection.prepareStatement("INSERT INTO spawn (name, X, Y, Z, worldName) VALUES (?, ?, ?, ?, ?)");
        statement17.setString(1, "spawn");
        statement17.setDouble(2, X);
        statement17.setDouble(3, Y);
        statement17.setDouble(4, Z);
        statement17.setString(5, worldName);
        statement17.executeUpdate();
        statement17.close();
        player.sendMessage(ChatColor.GREEN + "Spawn set successfully");
        statement17.close();
        statement19.close();
        result19.close();

    }

    public static void spawn(Player player) throws SQLException {

        PreparedStatement statement18 = connection.prepareStatement("SELECT X, Y, Z, worldName FROM spawn WHERE name = 'spawn'");
        ResultSet result18 = statement18.executeQuery();
        if (!result18.next()){
            player.sendMessage(ChatColor.RED + "No spawn point set");
            return;
        }
        double X = result18.getDouble("X");
        double Y = result18.getDouble("Y");
        double Z = result18.getDouble("Z");
        World world = MyFirstPlugin.getWorldByName(result18.getString("worldName"));
        Location location = new Location(world, X, Y, Z);
        player.teleport(location);
        statement18.close();
        result18.close();

    }

    public static void removeSpawn(Player player) throws SQLException{
        PreparedStatement statement21 = connection.prepareStatement("DELETE FROM spawn WHERE name = 'spawn'");
        statement21.executeUpdate();
        statement21.close();
        player.sendMessage(ChatColor.RED + "Warp successfully deleted");
    }

}
