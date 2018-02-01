package com.rapidprototyping.manbot.controller;

import com.rapidprototyping.manbot.Manbot;
import com.rapidprototyping.manbot.controller.impl.ProjectOperationCommandHelper;
import com.rapidprototyping.manbot.controller.impl.HelpCommandHandler;
import com.rapidprototyping.manbot.controller.impl.ProjectCommandHandler;
import com.rapidprototyping.manbot.model.command.Command;
import com.rapidprototyping.manbot.model.command.CommandParser;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.*;

/**
 *
 * @author Pavan C (pavan407)
 */
public class CommandListener extends ListenerAdapter
{
    private Manbot bot;

    private Map<String, CommandHandler> handlers = new HashMap<>();
    private CommandParser parser;

    public CommandListener(Manbot bot, CommandParser parser)
    {
        this.bot = bot;
        this.parser = parser;

        registerDefaults();
    }

    private void registerDefaults()
    {
        registerHandler("help", new HelpCommandHandler());
        registerHandler("projects", new ProjectCommandHandler());
        registerHandler("project", new ProjectOperationCommandHelper());
    }

    public CommandListener registerHandler(String name, CommandHandler handler)
    {
        handlers.put(name, handler);
        return this;
    }

    public void unregisterHandler(String name)
    {
        handlers.remove(name);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        MessageChannel ch = event.getChannel();
        User sender = event.getAuthor();
        String input = msg.getContentDisplay();
        if (!parser.isCommandCandidate(input))
            return;

        Command cmd = parser.parseCommand(input);
        CommandHandler handler = handlers.get(cmd.getName());
        if (handler == null)
        {
            ch.sendMessage(sender.getAsMention() + ", the requested command doesn't exist!").queue();
            return;
        }
        // TODO Compare required user type here
        if (!handler.getRequiredArgumentLength().conformsByPolicy(cmd.getArgs().size()))
        {
            sendUsage(ch, sender, handler);
            return;
        }

        try
        {
            handler.handle(new CommandContext(bot, this, msg.getAuthor(), msg.getChannel(), cmd));
        } catch(Exception e)
        {
            e.printStackTrace();
            sendUsage(ch, sender, handler);
        }
    }

    private void sendUsage(MessageChannel ch, User user, CommandHandler handler)
    {
        ch.sendMessage(user.getAsMention() + ", that didn't work! Here's the usage for " + handler.getName() + ":\n" +
                handler.getUsage()).queue();
    }

    public Collection<CommandHandler> getHandlers()
    {
        return new ArrayList<>(handlers.values());
    }
}
