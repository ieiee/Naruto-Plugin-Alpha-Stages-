package org.minecraft.minecraft.DatabaseCommands;

import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class chakaraSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static Integer getChakaraLevel(String playerName) throws SQLException {
        PreparedStatement statement73 = connection.prepareStatement("SELECT chakaraLevel FROM chakara WHERE playerName = ?");
        statement73.setString(1, playerName);
        ResultSet result73 = statement73.executeQuery();
        return result73.getInt("chakaraLevel");
    }

}
