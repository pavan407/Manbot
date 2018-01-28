package com.rapidprototyping.manbot.model.command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pavan C (pavan407)
 */
public class SimpleCommandParser implements CommandParser
{
    @Override
    public boolean isCommandCandidate(String input)
    {
        return input.startsWith("!");
    }

    @Override
    public Command parseCommand(String input)
    {
        try
        {
            List<String> args = new ArrayList<>();
            String name;
            int nameIndex = input.indexOf(" ");
            if (nameIndex == -1)
                name = input.substring(1);
            else
            {
                name = input.substring(1, nameIndex);
                input = input.substring(nameIndex);
                while (input != null)
                {
                    input = input.trim();
                    if (input.length() <= 0)
                        break;

                    // String
                    if (input.charAt(0) == '"')
                    {
                        String str;
                        int strEndIndex = input.indexOf("\"", 1);
                        if (strEndIndex == -1)
                        {
                            str = input;
                            input = null;
                        } else
                        {
                            str = input.substring(1, strEndIndex);
                            input = input.substring(strEndIndex + 1);
                        }
                        args.add(str);
                    }

                    // Argument
                    else
                    {
                        String arg;
                        int argEndIndex = input.indexOf(" ");
                        if (argEndIndex == -1)
                        {
                            arg = input;
                            input = null;
                        }
                        else
                        {
                            arg = input.substring(0, argEndIndex);
                            input = input.substring(argEndIndex);
                        }
                        args.add(arg);
                    }
                }
            }
            return new Command(name, args);
        } catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
