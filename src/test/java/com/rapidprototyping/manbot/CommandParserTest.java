package com.rapidprototyping.manbot;

import com.rapidprototyping.manbot.model.command.Command;
import com.rapidprototyping.manbot.model.command.CommandParser;
import com.rapidprototyping.manbot.model.command.SimpleCommandParser;
import org.junit.Test;

public class CommandParserTest
{
    private CommandParser parser = new SimpleCommandParser();

    @Test
    public void parseInput() throws Exception
    {
        Command c = parser.parseCommand("!kick \"my name\" \"sdfsdf sdfsdf f\"");
        System.out.println(c.getName());
        c.getArgs().forEach(System.out::println);
    }
}
