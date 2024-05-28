package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class tprSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    public static void insert(Player player, String sender, String receiver) throws SQLException {

        PreparedStatement statement30 = connection.prepareStatement("SELECT * FROM tpr WHERE sender = ?");
        statement30.setString(1, sender);
        ResultSet result30 = statement30.executeQuery();
        if (result30.next()){
            player.sendMessage(ChatColor.RED + "You have already sent request to this player");
            return;
        }
        PreparedStatement statement31 = connection.prepareStatement("INSERT INTO tpr (sender, receiver) VALUES (?, ?)");
        statement31.setString(1, sender);
        statement31.setString(2, receiver);
        statement31.executeUpdate();
        statement31.close();
        statement30.close();
        result30.close();
        player.sendMessage(ChatColor.GOLD + "TPR Sent :)");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                remove(player.getName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, 30, TimeUnit.SECONDS);
        Player pReceiver = Bukkit.getPlayer(receiver);
        assert pReceiver != null;
        pReceiver.sendMessage(ChatColor.GOLD + sender + " wants to teleport to you \n please use /tpa");
    }

    public static void remove(String sender) throws SQLException{
        PreparedStatement statement33 = connection.prepareStatement("SELECT * FROM tpr WHERE sender = ?");
        statement33.setString(1, sender);
        ResultSet result33 = statement33.executeQuery();
        if(!(result33.next())){
            statement33.close();
            result33.close();
            return;
        }
        PreparedStatement statement34 = connection.prepareStatement("DELETE FROM tpr WHERE sender = ?");
        statement34.setString(1, sender);
        statement34.executeUpdate();
        statement34.close();
    }

    public static void tprAccept(Player player, String receiver, String sender) throws SQLException{

        PreparedStatement statement35 = connection.prepareStatement("SELECT * FROM tpr WHERE receiver = ?");
        PreparedStatement statement38 = connection.prepareStatement("DELETE FROM tpr WHERE receiver = ?");
        statement38.setString(1, receiver);
        statement35.setString(1, receiver);
        ResultSet result35 = statement35.executeQuery();
        if(!(result35.next())){
            statement38.close();
            statement35.close();
            result35.close();
            player.sendMessage(ChatColor.RED + "There is no any request from " + sender);
            return;
        }
        statement38.executeUpdate();
        statement38.close();
        Player pSender = Bukkit.getPlayer(sender);
        assert pSender != null;
        pSender.teleport(player.getLocation());
    }

    public static void tprReject(Player player, String receiver, String Sender) throws SQLException{
        PreparedStatement statement36 = connection.prepareStatement("SELECT * FROM tpr WHERE receiver = ?");
        statement36.setString(1, receiver);
        ResultSet result36 = statement36.executeQuery();
        if (!(result36.next())){
            statement36.close();
            result36.close();
            player.sendMessage(ChatColor.RED + "There is no request received from " + Sender);
            return;
        }
        PreparedStatement statement37 = connection.prepareStatement("DELETE FROM tpr WHERE receiver = ?");
        statement37.setString(1, receiver);
        statement37.executeUpdate();
        statement37.close();
        Player pSender = Bukkit.getPlayer(Sender);
        assert pSender != null;
        pSender.sendMessage(ChatColor.YELLOW + "Your tp request was rejected by " + receiver);
        player.sendMessage(ChatColor.YELLOW + "Request rejected");
    }

    public static void tprCancel(Player player, String receiver) throws SQLException{
        PreparedStatement statement39 = connection.prepareStatement("SELECT * FROM tpr WHERE sender = ? AND receiver = ?");
        statement39.setString(1, player.getName());
        statement39.setString(2, receiver);
        ResultSet result38 = statement39.executeQuery();
        if (!(result38.next())){
            statement39.close();
            result38.close();
            player.sendMessage(ChatColor.RED + "You have no request pending to be accepted to the player " + receiver);
            return;
        }
        PreparedStatement statement40 = connection.prepareStatement("DELETE FROM tpr WHERE sender = ? AND receiver = ?");
        statement40.setString(1, player.getName());
        statement40.setString(2, receiver);
        statement40.executeUpdate();
        statement40.close();
        player.sendMessage(ChatColor.YELLOW + "Tp Request Canceled");
    }
}
