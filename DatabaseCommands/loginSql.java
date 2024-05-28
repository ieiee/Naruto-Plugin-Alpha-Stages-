package org.minecraft.minecraft.DatabaseCommands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.minecraft.minecraft.MyFirstPlugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class loginSql {

    public static Connection connection = MyFirstPlugin.getConnection();

    // got connection object from main plugin class from MyFirstPlugin

    public static void createTable() throws SQLException {

        Statement statement = connection.createStatement();

        String cmd = "CREATE TABLE IF NOT EXISTS login (" + "username TEXT PRIMARY KEY," + "password TEXT," + "loggedIn TEXT" + ")";

        statement.executeUpdate(cmd);
        statement.close();

        PreparedStatement statement11 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS warp (name TEXT PRIMARY KEY, X DOUBLE, Y DOUBLE, Z DOUBLE, worldName TEXT)");
        statement11.executeUpdate();
        statement11.close();

        PreparedStatement statement16 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS spawn (name TEXT, X DOUBLE, Y DOUBLE, Z DOUBLE, worldName TEXT)");
        statement16.executeUpdate();
        statement16.close();

        PreparedStatement statement22 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS dback (playerName TEXT, X DOUBLE, Y DOUBLE, Z DOUBLE, worldName TEXT)");
        statement22.executeUpdate();
        statement22.close();

        PreparedStatement statement27 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS tpr (sender TEXT, receiver TEXT)");
        statement27.executeUpdate();
        statement27.close();

        PreparedStatement statement41 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS balanceSheet (accountName TEXT, totalBalance DOUBLE, pocketBalance DOUBLE, bankBalance DOUBLE)");
        statement41.executeUpdate();
        statement41.close();

        PreparedStatement statement55 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS skills (playerName TEXT, waterBalloon TEXT, rubberBalloon TEXT, airBalloon TEXT)");
        statement55.executeUpdate();
        statement55.close();

        PreparedStatement statement70 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS chakara (playerName TEXT, chakaraLevel INTEGER)");
        statement70.executeUpdate();
        statement70.close();

        PreparedStatement statement74 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS reanimation (playerName TEXT, test TEXT)");
        statement74.executeUpdate();
        statement74.close();

        PreparedStatement statement78 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS playerDetails (playerName TEXT, villageName TEXT, clanName TEXT, rank TEXT, team TEXT, bio TEXT, status TEXT)");
        statement78.executeUpdate();
        statement78.close();
    }

    // create table function

    public static boolean authPlayer(Player player) throws SQLException {
        String name = player.getName();
        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM login WHERE username = ?");
        statement1.setString(1, name);
        ResultSet result1 = statement1.executeQuery();
        return !result1.next();
    }

    // this function authorizes that weather a player ever played in the server or have been registered in the login table

    public static void login(Player player, String password) throws SQLException {

        String id = player.getName();
        PreparedStatement statement6 = connection.prepareStatement("SELECT password FROM login WHERE username = ?");
        PreparedStatement statement10 = connection.prepareStatement("SELECT loggedIn FROM login WHERE username = ?");
        statement10.setString(1, player.getName());
        statement6.setString(1, id);
        ResultSet result6 = statement6.executeQuery();
        ResultSet result10 = statement10.executeQuery();
        if (result10.next()){
            if (!result10.getString("loggedIn").equalsIgnoreCase("notApproved")){
                player.sendMessage(ChatColor.RED + "You have already logged in");
                return;
            }
        }
        if (result6.next()) {

            if (result6.getString("password").equalsIgnoreCase("noPassword")){
                player.sendMessage(ChatColor.RED + "Please first register using /register <password>");
                return;
            }

            if (result6.getString("password").equals(password)) {
                PreparedStatement statement2 = connection.prepareStatement("UPDATE login SET loggedIn = 'yesApproved' WHERE username = ?;");
                statement2.setString(1, id);
                statement2.executeUpdate();
                statement2.close();
                player.setGameMode(GameMode.SURVIVAL);
                player.removePotionEffect(PotionEffectType.BLINDNESS);
                player.sendMessage(ChatColor.GREEN + "You are successfully logged in");
                player.sendTitle(ChatColor.BOLD + "" + ChatColor.YELLOW + "Welcome to the server",ChatColor.AQUA + "@" + player.getName() + " have a nice journey ahead");
                if (reanimationSql.checkSql(player.getName())){
                    player.setHealth(0);
                    player.sendMessage(ChatColor.YELLOW + "Sorry about that, but this will be happening again and again on every login if you remains dead");
                }
            }else{
                player.sendMessage(ChatColor.RED + "The password was incorrect");
            }
        }

        statement6.close();
        statement10.close();
        result6.close();
        result10.close();
    }

    // this login function is used by the login command

    public static void register(Player player, String password) throws SQLException {

        PreparedStatement statement7 = connection.prepareStatement("SELECT password FROM login WHERE username = ?");
        PreparedStatement statement8 = connection.prepareStatement("UPDATE login SET password = ? WHERE username = ?;");
        PreparedStatement statement9 = connection.prepareStatement("UPDATE login SET loggedIn = 'yesApproved' WHERE username = ?;");
        statement7.setString(1, player.getName());
        ResultSet result7 = statement7.executeQuery();
        if (result7.getString("password").equals("noPassword")){
            statement8.setString(1, password);
            statement8.setString(2, player.getName());
            statement9.setString(1, player.getName());
            statement8.executeUpdate();
            statement9.executeUpdate();
            player.setGameMode(GameMode.SURVIVAL);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.sendMessage(ChatColor.GREEN + "You are successfully Registered");
            player.sendTitle(ChatColor.BOLD + "" + ChatColor.YELLOW + "Welcome to the server",ChatColor.AQUA + "@" + player.getName() + " have a nice journey ahead");

        }else{
            player.sendMessage(ChatColor.RED + "You have already logged in with this account on the server");
        }
        result7.close();
        statement8.close();
        statement9.close();
        statement7.close();

    }

    // this register command is used by the register command

    public static String getCheck(Player player) throws SQLException {

        String check = "notApproved";
        PreparedStatement statement3 = connection.prepareStatement("SELECT loggedIn FROM login WHERE username = ?");
        statement3.setString(1, player.getName());
        ResultSet result = statement3.executeQuery();
        if (result.next())
            check = result.getString("loggedIn");
        result.close();
        statement3.close();
        return check;
    }

    // getCheck() function is used to check weather a player joined have logged in or not

    public static String getPassword(Player player) throws SQLException {

        String check2 = "noPassword";
        PreparedStatement statement5 = connection.prepareStatement("SELECT password FROM login WHERE username = ?");
        statement5.setString(1, player.getName());
        ResultSet result5 = statement5.executeQuery();
        if (result5.next())
            check2 = result5.getString("password");
        result5.close();
        statement5.close();
        return check2;
    }

    // this getPassword() function is used to get the password of the mentioned player

    public static void setNo(Player player) throws SQLException {

        String id = player.getName();
        PreparedStatement statement4 = connection.prepareStatement("UPDATE login SET loggedIn = 'notApproved' WHERE username = ?;");
        statement4.setString(1, id);
        statement4.executeUpdate();
        statement4.close();
    }

    // after the player had leaved the server setNo() function is used to set loggedIn column data to notApproved

    public static void insertPlayer() throws SQLException {

        List<String> listPlayers = new ArrayList<>();
        List<String> allPlayers = MyFirstPlugin.getPlayers();
        PreparedStatement statement5 = connection.prepareStatement("SELECT username FROM login");
        ResultSet result2 = statement5.executeQuery();

        while (result2.next()) {
            String columnData = result2.getString("username");
            listPlayers.add(columnData);
        }

        result2.close();
        statement5.close();

        for (String playerName : allPlayers) {
            boolean check1 = false;
            for (String playerNameCheck : listPlayers) {
                if (playerName.equalsIgnoreCase(playerNameCheck)) {
                    check1 = true;
                    break;
                }
            }
            if (!check1) {
                PreparedStatement statement1 = connection.prepareStatement("INSERT INTO login (username, password, loggedIn) VALUES (?, ?, ?)");
                PreparedStatement statement43 = connection.prepareStatement("INSERT INTO balanceSheet (accountName, totalBalance, pocketBalance, bankBalance) VALUES (?, ?, ?, ?)");
                PreparedStatement statement56 = connection.prepareStatement("INSERT INTO skills (playerName, waterBalloon, rubberBalloon, airBalloon) VALUES (?, ?, ?, ?)");
                PreparedStatement statement71 = connection.prepareStatement("INSERT INTO chakara (playerName, chakaraLevel) VALUES (?, 100)");
                statement71.setString(1, playerName);
                statement56.setString(1, playerName);
                statement56.setString(2, "notYet");
                statement56.setString(3, "notYet");
                statement56.setString(4, "notYet");
                statement1.setString(1, playerName);
                statement1.setString(2, "noPassword");
                statement1.setString(3, "notApproved");
                statement43.setString(1, playerName);
                statement43.setDouble(2, 1000);
                statement43.setDouble(3, 0);
                statement43.setDouble(4, 1000);
                statement1.executeUpdate();
                statement43.executeUpdate();
                statement56.executeUpdate();
                statement71.executeUpdate();
                statement1.close();
                statement43.close();
                statement56.close();
                statement71.close();
                System.out.println(playerName);
            }
        }


    }

    // this insertPlayer() function is used to insert the data of all the players which have joined the server since now

    public static void playerAdd(Player player) throws SQLException {

        if (authPlayer(player)) {
            PreparedStatement statement5 = connection.prepareStatement("INSERT INTO login (username, password, loggedIn) VALUES (?, ?, ?)");
            PreparedStatement statement42 = connection.prepareStatement("INSERT INTO balanceSheet (accountName, totalBalance, pocketBalance, bankBalance) VALUES (?, ?, ?, ?)");
            PreparedStatement statement57 = connection.prepareStatement("INSERT INTO skills (playerName, waterBalloon, rubberBalloon, airBalloon) VALUES (?, ?, ?, ?)");
            PreparedStatement statement72 = connection.prepareStatement("INSERT INTO chakara (playerName, chakaraLevel) VALUES (?, 100)");
            statement72.setString(1, player.getName());
            statement57.setString(1, player.getName());
            statement57.setString(2, "notYet");
            statement57.setString(3, "notYet");
            statement57.setString(4, "notYet");
            statement5.setString(1, player.getName());
            statement5.setString(2, "noPassword");
            statement5.setString(3, "notApproved");
            statement42.setString(1, player.getName());
            statement42.setDouble(2, 1000);
            statement42.setDouble(3, 0);
            statement42.setDouble(4, 1000);
            statement5.executeUpdate();
            statement42.executeUpdate();
            statement57.executeUpdate();
            statement72.executeUpdate();
            statement5.close();
            statement42.close();
            statement57.close();
            statement72.close();

        }

    }

    // this is little bit different from insertPlayer(), this only inserts the data of the player when a new player joins in the server
}
