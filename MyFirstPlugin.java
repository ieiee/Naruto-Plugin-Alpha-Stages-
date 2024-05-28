package org.minecraft.minecraft;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.minecraft.minecraft.DatabaseCommands.playerDetailSql;
import org.minecraft.minecraft.chakara.chakaraControl;
import org.minecraft.minecraft.commands.*;
import org.minecraft.minecraft.commands.economyCommands.balance;
import org.minecraft.minecraft.commands.economyCommands.bank;
import org.minecraft.minecraft.commands.loginCommands.login;
import org.minecraft.minecraft.commands.loginCommands.register;
import org.minecraft.minecraft.commands.spawnCommands.removeSpawn;
import org.minecraft.minecraft.commands.spawnCommands.setSpawn;
import org.minecraft.minecraft.commands.spawnCommands.spawn;
import org.minecraft.minecraft.commands.tprCommands.tpa;
import org.minecraft.minecraft.commands.tprCommands.tpc;
import org.minecraft.minecraft.commands.tprCommands.tpr;
import org.minecraft.minecraft.commands.tprCommands.tpt;
import org.minecraft.minecraft.commands.warpCommands.delWarp;
import org.minecraft.minecraft.commands.warpCommands.setWarp;
import org.minecraft.minecraft.commands.warpCommands.warp;
import org.minecraft.minecraft.discord.BotStart;
import org.minecraft.minecraft.listeners.*;
import org.minecraft.minecraft.DatabaseCommands.loginSql;
import org.minecraft.minecraft.listeners.blockBreak.veinMiner;
import org.minecraft.minecraft.listeners.reanimationJutsu.reanimationZombie;
import org.minecraft.minecraft.CustomToolsAndWeapons.recipes.recipesClass;
import org.minecraft.minecraft.listeners.economyListeners.guiListeners;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// all the minecraft listeners and commands are present in their respective packages
// all the discord listeners and commands are present in their respective packages


public class MyFirstPlugin extends JavaPlugin {

    private static MyFirstPlugin instance;

    public static List<String> allPlayers = new ArrayList<>();

    public static List<String> getPlayers(){
        return allPlayers;
    }

    @Override
    public void onEnable()
    {
        for (Player playerOnline : Bukkit.getOnlinePlayers()){
            allPlayers.add(playerOnline.getName());
        }
        // this is for adding all the online players to the list named allPlayers

        for (OfflinePlayer playerOffline : Bukkit.getOfflinePlayers()){
            allPlayers.add(playerOffline.getName());
        }
        // this is for adding all the offline players to the list named allPlayers

        System.out.println(allPlayers);

        instance = this;

        // made an instance of this class

        saveDefaultConfig();

        // created a default config file of the plugin

        File leaveMessageFile = new File(getDataFolder(), "leave_messages.yml");
        if (!leaveMessageFile.exists()){
            saveResource("leave_messages.yml", false);
        }
        leaveMessageConfig = YamlConfiguration.loadConfiguration(leaveMessageFile);

        // player leave message configuration file


        File joinMessageFile = new File(getDataFolder(), "join_messages.yml");
        if (!joinMessageFile.exists()) {
            saveResource("join_messages.yml", false);
        }
        joinMessageConfig = YamlConfiguration.loadConfiguration(joinMessageFile);

        // player join message configuration file

        // when the plugin enables

        BotStart.botStart();
        // sets connection to the discord bot

        getLogger().info("We are ready to go");
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new MessageListener(), this);
        getServer().getPluginManager().registerEvents(new playerMovement(), this);
        getServer().getPluginManager().registerEvents(new PlayerKick(), this);
        getServer().getPluginManager().registerEvents(new reanimationZombie(), this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new veinMiner(), this);
        getServer().getPluginManager().registerEvents(new guiListeners(), this);
        getServer().getPluginManager().registerEvents(new toolsListeners(), this);
        getServer().getPluginManager().registerEvents(new onPlayerRespawn(), this);
        Objects.requireNonNull(getCommand("login")).setExecutor(new login());
        Objects.requireNonNull(getCommand("register")).setExecutor(new register());
        Objects.requireNonNull(getCommand("setWarp")).setExecutor(new setWarp());
        Objects.requireNonNull(getCommand("warp")).setExecutor(new warp());
        Objects.requireNonNull(getCommand("delWarp")).setExecutor(new delWarp());
        Objects.requireNonNull(getCommand("home")).setExecutor(new home());
        Objects.requireNonNull(getCommand("setSpawn")).setExecutor(new setSpawn());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new spawn());
        Objects.requireNonNull(getCommand("removeSpawn")).setExecutor(new removeSpawn());
        Objects.requireNonNull(getCommand("dback")).setExecutor(new dback());
        Objects.requireNonNull(getCommand("tpt")).setExecutor(new tpt());
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new tpa());
        Objects.requireNonNull(getCommand("tpr")).setExecutor(new tpr());
        Objects.requireNonNull(getCommand("tpc")).setExecutor(new tpc());
        Objects.requireNonNull(getCommand("bank")).setExecutor(new bank());
        Objects.requireNonNull(getCommand("balance")).setExecutor(new balance());
        Objects.requireNonNull(getCommand("reAnimation")).setExecutor(new reAnimation());
        Objects.requireNonNull(getCommand("profile")).setExecutor(new profile());

        connectDb();

        // connectDb() is used to setup connection with database.db

        try {
            loginSql.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // created login table

        try {
            loginSql.insertPlayer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        veinMiner.addBlocks();
        recipesClass.cAxeRecipe();
        recipesClass.cPickaxeRecipe();
        recipesClass.toadsBread();
        recipesClass.rasenshurikens();
        recipesClass.heibeirus();
        recipesClass.wBalloons();
        recipesClass.rBalloons();
        recipesClass.aBalloons();
        recipesClass.fireballs();
        recipesClass.fireStyles();

        playerDetailSql.createVillagesAndClans();
        // inserted all the player in login table using the list allPlayer

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player == null){
                        return;
                    }
                    chakaraControl.regenerateChakara(player);
                }
            }
        }.runTaskTimer(this, 20, 10);

    }

    private static FileConfiguration leaveMessageConfig;
    public static FileConfiguration getLeaveMessage(){
        return leaveMessageConfig;
    }

    private static FileConfiguration joinMessageConfig;
    public static FileConfiguration getJoinMessage(){
        return joinMessageConfig;
    }

    public static MyFirstPlugin getPlugin(){
        return instance;
    }

    private static Connection connection;

    public void connectDb(){

        // function for connecting to the database.db

        try{
        Class.forName("org.sqlite.JDBC");
        String dataFolder = getPlugin().getDataFolder().getAbsolutePath();
        String path = dataFolder + "/database.db";
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        getLogger().info("Connected to Database");
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void disconnectDb(){

        // function for disconnecting from the database.db
        try{
            if (connection != null){
                connection.close();
                getLogger().info("Disconnected from Database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static World getWorldByName(String name){
        for (World world: Bukkit.getWorlds()){
            if (world.getName().equalsIgnoreCase(name)){
                return world;
            }
        }
        return null;
    }

    // returns connection object(accessed from many other java classes)


    @Override
    public void onDisable() {

        // when plugin disables

        List<Player> setNoPlayers = new ArrayList<>();
        setNoPlayers.addAll(Bukkit.getOnlinePlayers());
        setNoPlayers.addAll(Bukkit.getOnlinePlayers());
        for (Player player : setNoPlayers){
            try {
                assert player != null;
                loginSql.setNo(player);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        disconnectDb();

        // disconnected from database.db

        getLogger().info("Server is stopping!");

        BotStart.send("Server", "🔴Server is offline");

        // message send to discord server
    }
}
