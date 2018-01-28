package com.rapidprototyping.manbot.controller;

import com.rapidprototyping.manbot.model.command.ArgumentLength;

/**
 *
 * @author Pavan C (pavan407)
 */
public interface CommandHandler
{
    String getName();

    void handle(CommandContext ctx);

    default ArgumentLength getArgumentLength()
    {
        return ArgumentLength.anyLength();
    }

    default String getUsage()
    {
        return "N/A";
    }

    default String getAuthor()
    {
        return "Anonymous";
    }

    default String getDescription()
    {
        return "N/A";
    }
}
