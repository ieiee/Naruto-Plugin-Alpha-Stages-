package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class economySql {

    private static final Connection connection = MyFirstPlugin.getConnection();

    public static void getBalance(Player player) throws SQLException {
        PreparedStatement statement44 = connection.prepareStatement("SELECT * FROM balanceSheet WHERE accountName = ?");
        statement44.setString(1, player.getName());
        ResultSet result44 = statement44.executeQuery();
        player.sendMessage(ChatColor.YELLOW + "Account Name - " + result44.getString("accountName") + "\nTotal Balance - " + ChatColor.GOLD + "Rs." + result44.getDouble("totalBalance") + ChatColor.YELLOW + "\nPocketBalance - " + ChatColor.GOLD + "Rs." + result44.getDouble("pocketBalance") + ChatColor.YELLOW + "\nBank Balance - " + ChatColor.GOLD + "Rs." + result44.getDouble("bankBalance"));
    }

    public static void withdraw(Player player, Double amount) throws SQLException {

        PreparedStatement statement46 = connection.prepareStatement("SELECT bankBalance FROM balanceSheet WHERE accountName = ?");
        statement46.setString(1, player.getName());
        ResultSet result46 = statement46.executeQuery();
        assert result46 != null;
        if (amount > result46.getDouble("bankBalance")) {
            player.sendMessage("The amount you have entered is more then you have deposited in your bank account XD");
            statement46.close();
            result46.close();
        } else {
            PreparedStatement statement45 = connection.prepareStatement("UPDATE balanceSheet SET pocketBalance = pocketBalance + ?, bankBalance = bankBalance - ? WHERE accountName = ?;");
            statement45.setDouble(1, amount);
            statement45.setDouble(2, amount);
            statement45.setString(3, player.getName());
            statement45.executeUpdate();
            statement45.close();
            statement46.close();
            result46.close();
            getBalance(player);
        }
    }

    public static void deposit(Player player, Double amount) throws SQLException {

        PreparedStatement statement46 = connection.prepareStatement("SELECT pocketBalance FROM balanceSheet WHERE accountName = ?");
        statement46.setString(1, player.getName());
        ResultSet result46 = statement46.executeQuery();
        assert result46 != null;
        if (amount > result46.getDouble("pocketBalance")) {
            player.sendMessage("The amount you have entered is more then you have in your pocket XD");
            statement46.close();
            result46.close();
        } else {
            PreparedStatement statement45 = connection.prepareStatement("UPDATE balanceSheet SET pocketBalance = pocketBalance - ?, bankBalance = bankBalance + ? WHERE accountName = ?;");
            statement45.setDouble(1, amount);
            statement45.setDouble(2, amount);
            statement45.setString(3, player.getName());
            statement45.executeUpdate();
            statement45.close();
            statement46.close();
            result46.close();
            getBalance(player);
        }

    }

    public static void withdrawAll(Player player) throws SQLException {
        PreparedStatement statement47 = connection.prepareStatement("SELECT bankBalance FROM balanceSheet WHERE accountName = ?");
        statement47.setString(1, player.getName());
        ResultSet result47 = statement47.executeQuery();
        double bankBalance = result47.getDouble("bankBalance");
        PreparedStatement statement48 = connection.prepareStatement("UPDATE balanceSheet SET pocketBalance = pocketBalance + ?, bankBalance = 0 WHERE accountName = ?;");
        statement48.setDouble(1, bankBalance);
        statement48.setString(2, player.getName());
        statement48.executeUpdate();
        statement47.close();
        statement48.close();
        result47.close();
        getBalance(player);
    }

    public static void withdrawHalf(Player player) throws SQLException {
        PreparedStatement statement49 = connection.prepareStatement("SELECT bankBalance FROM balanceSheet WHERE accountName = ?");
        statement49.setString(1, player.getName());
        ResultSet result49 = statement49.executeQuery();
        double bankBalance = result49.getDouble("bankBalance");
        PreparedStatement statement50 = connection.prepareStatement("UPDATE balanceSheet SET pocketBalance = pocketBalance + ?, bankBalance = ? WHERE accountName = ?;");
        statement50.setDouble(1, bankBalance / 2);
        statement50.setDouble(2, bankBalance / 2);
        statement50.setString(3, player.getName());
        statement50.executeUpdate();
        statement49.close();
        statement50.close();
        result49.close();
        getBalance(player);
    }

    public static void depositAll(Player player) throws SQLException {
        PreparedStatement statement51 = connection.prepareStatement("SELECT pocketBalance FROM balanceSheet WHERE accountName = ?");
        statement51.setString(1, player.getName());
        ResultSet result51 = statement51.executeQuery();
        double pocketBalance = result51.getDouble("pocketBalance");
        PreparedStatement statement52 = connection.prepareStatement("UPDATE balanceSheet SET pocketBalance = 0, bankBalance = bankBalance + ? WHERE accountName = ?;");
        statement52.setDouble(1, pocketBalance);
        statement52.setString(2, player.getName());
        statement52.executeUpdate();
        statement51.close();
        statement52.close();
        result51.close();
        getBalance(player);
    }

    public static void depositHalf(Player player) throws SQLException {
        PreparedStatement statement53 = connection.prepareStatement("SELECT pocketBalance FROM balanceSheet WHERE accountName = ?");
        statement53.setString(1, player.getName());
        ResultSet result53 = statement53.executeQuery();
        double pocketBalance = result53.getDouble("pocketBalance");
        PreparedStatement statement54 = connection.prepareStatement("UPDATE balanceSheet SET pocketBalance = ?, bankBalance = bankBalance + ? WHERE accountName = ?;");
        statement54.setDouble(1, pocketBalance / 2);
        statement54.setDouble(2, pocketBalance / 2);
        statement54.setString(3, player.getName());
        statement54.executeUpdate();
        statement53.close();
        statement54.close();
        result53.close();
        getBalance(player);
    }
}
