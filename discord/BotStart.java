package org.minecraft.minecraft.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.minecraft.minecraft.MyFirstPlugin;
import org.minecraft.minecraft.discord.listeners.DiscordListener;

import java.awt.*;

public class BotStart {

    private static JDA jda;
    private static String text;

    public static void botStart(){

        String token = MyFirstPlugin.getPlugin().getConfig().getString("bot_token");

        // bot token taken from the config file of the plugin

        text = MyFirstPlugin.getPlugin().getConfig().getString("text_channel_id");

        // text channel id taken from config file of the plugin

        if (token != null){
            if (token.equals("BOT_TOKEN HERE")){
                // check weather the bot token is mentioned as default, if yes it will not connect to the discord bot
                System.out.println(ChatColor.RED + "You have not mentioned Bot Token in config.yml in in plugins folder in your server directory");

            }else {
                if (text != null){
                    if (text.equals("TEXT_CHANNEL_ID HERE")){

                        // checks weather the text channel id is mentioned as default, if yes it will not connect to the discord bot

                        System.out.println(ChatColor.RED + "You have not mentioned Text Channel ID in config.yml in plugins folder in your server directory");

                    }else {

                        jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT).build();
                        jda.addEventListener(new DiscordListener());

                        // successfully connected to the discord bot if both the bot token and text channel id is mentioned in the config file

                    }
                }
            }

        }

    }

    public static void send(String author, String message){
        // function used to send messages from minecraft chat to discord
        if (jda != null) {
            // getting assurance for the successful connection
            String message1 = "[" + "Minecraft" + "]" + "[" + author + "]: " + message;
            TextChannel channel = jda.getTextChannelById(text);
            if (channel != null)
                channel.sendMessage(message1).queue();
        }else{
            System.out.println("There is no discord server connected to the server");
        }
    }

    public static void playerJoinMessage(String playerName){
        if (jda != null){
            TextChannel channel = jda.getTextChannelById(text);
            EmbedBuilder playerJoinEmbed = new EmbedBuilder();
            playerJoinEmbed.setAuthor("Minecraft");
            playerJoinEmbed.setTitle(playerName + " joined the server");
            playerJoinEmbed.setColor(Color.YELLOW);
            if (channel != null)
                channel.sendMessageEmbeds(playerJoinEmbed.build()).queue();
        }
    }

    public static void playerLeaveMessage(String playerName){
        if (jda != null){
            TextChannel channel = jda.getTextChannelById(text);
            EmbedBuilder playerLeaveEmbed = new EmbedBuilder();
            playerLeaveEmbed.setColor(Color.RED);
            playerLeaveEmbed.setTitle(playerName + " left the server");
            playerLeaveEmbed.setAuthor("Minecraft");
            if (channel != null){
                channel.sendMessageEmbeds(playerLeaveEmbed.build()).queue();
            }
        }
    }

    public static void rec(String author, String message){
        // function used to receive and send the messages to minecraft chat
        Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.WHITE + "[" + ChatColor.BOLD + ChatColor.BLUE + "Discord" + ChatColor.WHITE + ChatColor.BOLD + "]" + "[" + ChatColor.AQUA + ChatColor.BOLD + author + ChatColor.WHITE + ChatColor.BOLD + "]: " + ChatColor.WHITE + message);
    }

}


