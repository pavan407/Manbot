package com.rapidprototyping.manbot.controller.impl;

import com.rapidprototyping.manbot.controller.CommandContext;
import com.rapidprototyping.manbot.controller.CommandHandler;

public class HelpCommandHandler implements CommandHandler
{
    @Override
    public String getName()
    {
        return "help";
    }

    @Override
    public void handle(CommandContext ctx)
    {
        StringBuilder projects = new StringBuilder();
        ctx.getListener().getHandlers().stream().filter((handler) -> handler != this).forEach((handler) ->
            projects.append("!").append(handler.getName()).append(" - ").append(handler.getDescription()).append("\n"));
        ctx.getChannel().sendMessage(
                "Hey " + ctx.getSender().getAsMention() + ", here's a list of commands you can use with me:\n" + projects).queue();
    }
}
