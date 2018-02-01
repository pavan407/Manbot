package com.rapidprototyping.manbot.model.command;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nirodha
 */
public class SimpleCommandParser implements CommandParser
{
    private Pattern pattern = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");

    /**
     *
     * @param input must have at least one non-whitespace character
     * @return determines whether or not the lead character of the input is a '!' character or not
     */
    @Override
    public boolean isCommandCandidate(String input) {
        if(input==null || input.trim().length() == 0){
            return false;
        }else{
            return input.trim().charAt(0) == '!';
        }
    }

    /**
     *
     * @param input should come in the form !<input> to be a valid command. If non-! characters are first, the isCommandCandidate method won't recognize the input as a command.
     * @return
     */
    @Override
    public Command parseCommand(String input) {

        ArrayList<String> tokenizedMessage = new ArrayList<>();
        Matcher regexMatcher;
        regexMatcher = pattern.matcher(input);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // DQ_TOKEN
                tokenizedMessage.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // SQ_TOKEN
                tokenizedMessage.add(regexMatcher.group(2));
            } else {
                // SIMPLE_TOKEN
                tokenizedMessage.add(regexMatcher.group());
            }
        }
        String name = tokenizedMessage.get(0);
        tokenizedMessage.remove(0);
        ArrayList<String> args = tokenizedMessage;
        return new Command(name,tokenizedMessage);
    }
}
