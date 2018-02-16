package com.manbot.command;

import com.manbot.model.Permissions;
import com.manbot.model.poll.Poll;
import com.manbot.model.poll.PollManager;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static com.manbot.model.Permissions.MENTOR;

/**
 * @author Pavan C. (pavan407)
 */
public class PollCommandHandler extends CommandHandler
{
    public PollCommandHandler()
    {
        super("poll",
                "Poll operations",
                ArgumentPolicies.greaterThan(1),
                "[create/start] [name] [duration in minutes] [choices...]\n[end/stop] [name] [reason]",
                Permissions.greaterThan(MENTOR));
    }

    @Override
    public void handle(CommandEvent event)
    {
        Command cmd = event.getCommand();
        String op = cmd.getNextArgument();
        switch(op)
        {
            case "create":
            case "start":
            {
                String name = cmd.getNextArgument();
                Duration duration = Duration.ofMinutes(Long.parseLong(cmd.getNextArgument()));
                Set<String> choices = new HashSet<>(cmd.getRemainingArguments());
                Poll poll = new Poll(name, event.getChannel(), duration, choices);
                PollManager.submit(poll);

                event.getChannel().sendMessage("Hey " + event.getMember().getAsMention()
                        + ", the poll has been and has started.").queue();
            }
            case "end":
            case "stop":
            {}
        }
    }
}
