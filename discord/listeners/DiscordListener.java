package org.minecraft.minecraft.discord.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.minecraft.minecraft.discord.BotStart;

public class DiscordListener extends ListenerAdapter {

    // this class use to send messages to the minecraft server when any message arrives to discord server

    @Override
    public void onReady(@NotNull ReadyEvent event){

        // when bot gets ready to go

        BotStart.send("Server", "🟢Server is online");

        // sends message to the server that server has started

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        // when message received on the discord server

        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();
        // message content in it
        String author = event.getAuthor().getName();
        // message's author name in it
        BotStart.rec(author, message);
        // used MyFirstPlugin java class to send message to minecraft(check the function for more clearance in understanding)
    }
}