package com.rapidprototyping.manbot.controller;

import com.rapidprototyping.manbot.Manbot;
import com.rapidprototyping.manbot.model.command.Command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author Pavan C (pavan407)
 */
public class CommandContext
{
    private final Manbot bot;
    private CommandListener listener;
    private final User sender;
    private final MessageChannel channel;
    private final Command command;

    public CommandContext(Manbot bot, CommandListener listener, User sender, MessageChannel channel, Command command)
    {
        this.bot = bot;
        this.listener = listener;
        this.sender = sender;
        this.channel = channel;
        this.command = command;
    }

    public Manbot getBot()
    {
        return bot;
    }

    public CommandListener getListener()
    {
        return listener;
    }

    public User getSender()
    {
        return sender;
    }

    public MessageChannel getChannel()
    {
        return channel;
    }

    public Command getCommand()
    {
        return command;
    }
}
