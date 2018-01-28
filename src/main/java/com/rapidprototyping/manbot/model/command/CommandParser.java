package com.rapidprototyping.manbot.model.command;

public interface CommandParser
{
    boolean isCommandCandidate(String input);

    Command parseCommand(String input);
}
