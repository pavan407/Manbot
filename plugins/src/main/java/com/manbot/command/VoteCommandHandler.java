package com.manbot.command;

import com.manbot.model.poll.PollManager;

/**
 * @author Pavan C. (pavan407)
 */
public class VoteCommandHandler extends CommandHandler
{
    public VoteCommandHandler()
    {
        super("vote",
                "Submits your vote to a poll",
                ArgumentPolicies.fixed(2),
                "[poll] [choice]");
    }

    @Override
    public void handle(CommandEvent event)
    {
        String name = event.getCommand().getNextArgument();
        String choice = event.getCommand().getNextArgument();
        if (PollManager.exists(event.getChannel(), name))
        {
            PollManager.get(event.getChannel(), name).submitVote(event.getMember(), choice);
            event.getChannel().sendMessage("Hey " + event.getMember().getAsMention()
                    + ", your vote has been submitted!").queue();
        } else
            event.getChannel().sendMessage(event.getMember().getAsMention()
                    + ", we couldn't find that poll.").queue();
    }
}
