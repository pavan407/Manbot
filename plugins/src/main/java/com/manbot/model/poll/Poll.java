package com.manbot.model.poll;

import com.google.common.collect.ImmutableMap;
import com.manbot.Manbot;
import com.manbot.user.MessageFormattedException;
import com.manbot.user.User;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pavan C. (pavan407)
 */
public class Poll
{
    private final String name;
    private final MessageChannel channel;
    private final ImmutableMap<String, Set<User>> votes;
    private final Duration duration;
    private Duration remainingDuration;
    private boolean ended;
    private PollResults results;

    public Poll(String name, MessageChannel channel, Duration duration, Set<String> choices)
    {
        this.name = name;
        this.channel = channel;
        ImmutableMap.Builder<String, Set<User>> builder = ImmutableMap.builderWithExpectedSize(choices.size());
        choices.forEach(choice -> builder.put(choice, new HashSet<>()));
        votes = builder.build();
        this.duration = duration;
        remainingDuration = duration;
    }

    // TODO Switch to MessageFormattedExceptions
    public void submitVote(User voter, String choice)
    {
        checkIfActive();
        choice = choice.toLowerCase();
        if (!votes.containsKey(choice))
            throw new MessageFormattedException("Choice doesn't exist", "That choice doesn't exist");
        for (User user : votes.get(choice))
            if (user == voter)
                throw new MessageFormattedException("User has already voted", "You've already voted");

        votes.get(choice).add(voter);
    }

    public void tick(Duration tick)
    {
        checkIfActive();

        remainingDuration = remainingDuration.minus(tick);
        if (remainingDuration.isZero() || remainingDuration.isNegative())
            end();
        Manbot.pulseEvent(new PollMutationEvent(this, tick));
    }

    public PollResults end()
    {
        checkIfActive();

        ended = true;
        return getResults();
    }

    public String getName()
    {
        return name;
    }

    public MessageChannel getChannel()
    {
        return channel;
    }

    public Duration getDuration()
    {
        return duration;
    }

    public Duration getRemainingDuration()
    {
        return remainingDuration;
    }

    public Collection<String> getChoices()
    {
        return votes.keySet();
    }

    public boolean isActive()
    {
        return !ended;
    }

    public void checkIfActive()
    {
        if (hasEnded())
            throw new MessageFormattedException("Poll has already ended", "This poll's already ended");
    }

    public boolean hasEnded()
    {
        return !isActive();
    }

    private void checkIfEnded()
    {
        if (isActive())
            throw new MessageFormattedException("Poll is still active", "This poll's still active");
    }

    public PollResults getResults()
    {
        if (results == null)
        {
            checkIfEnded();
            results = new PollResults(votes);
        }
        return results;
    }
}
