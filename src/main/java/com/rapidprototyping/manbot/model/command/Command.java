package com.rapidprototyping.manbot.model.command;

import java.util.List;

/**
 *
 * @author Pavan C (pavan407)
 */
public class Command
{
    private final String name;
    private  final List<String> args; // TODO Make this immutable

    public Command(String name, List<String> args)
    {
        this.name = name;
        this.args = args;
    }

    public String getName()
    {
        return name;
    }

    public List<String> getArgs()
    {
        return args;
    }
}
