package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.entity.Player;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reanimationSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static boolean checkSql(String name) throws SQLException {
        PreparedStatement statement75 = connection.prepareStatement("SELECT * FROM reanimation WHERE playerName = ?");
        statement75.setString(1,name);
        ResultSet result75 = statement75.executeQuery();
        return result75.next();
    }

    public static void addPlayer(Player player) throws SQLException {
        if (checkSql(player.getName())){
            return;
        }
        PreparedStatement statement76 = connection.prepareStatement("INSERT INTO reanimation (playerName, test) VALUES (?, ?)");
        statement76.setString(1, player.getName());
        statement76.setString(2, "test");
        statement76.executeUpdate();
        statement76.close();
    }

    public static void removePlayer(Player player) throws SQLException {
        if (!checkSql(player.getName())){
            return;
        }
        PreparedStatement statement77 = connection.prepareStatement("DELETE FROM reanimation WHERE playerName = ?");
        statement77.setString(1, player.getName());
        statement77.executeUpdate();
        statement77.close();
    }

}
