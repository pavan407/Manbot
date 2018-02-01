package com.rapidprototyping.manbot.controller;

import com.rapidprototyping.manbot.model.command.ArgumentLength;
import com.rapidprototyping.manbot.model.user.UserType;

/**
 *
 * @author Pavan C (pavan407)
 */
public interface CommandHandler
{
    String getName();

    void handle(CommandContext ctx);

    default UserType getRequiredUserType()
    {
        return UserType.LEARNER;
    }

    default ArgumentLength getRequiredArgumentLength()
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
