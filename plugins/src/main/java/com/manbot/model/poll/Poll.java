package com.manbot.model.poll;

import com.google.common.collect.ImmutableMap;

import com.manbot.Manbot;
import com.manbot.user.UserFriendlyException;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.time.Duration;
import java.util.Arrays;
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
    private final ImmutableMap<String, Set<Member>> votes;
    private final Duration duration;
    private Duration remainingDuration;
    private boolean ended;
    private PollResults results;

    public Poll(String name, MessageChannel channel, Duration duration, Set<String> choices)
    {
        this.name = name;
        this.channel = channel;
        ImmutableMap.Builder<String, Set<Member>> builder = ImmutableMap.builderWithExpectedSize(choices.size());
        choices.forEach(choice -> builder.put(choice, new HashSet<>()));
        votes = builder.build();
        this.duration = duration;
        remainingDuration = duration;
    }

    public Poll(String name, MessageChannel channel, Duration duration, String... choices)
    {
        this(name, channel, duration, new HashSet<>(Arrays.asList(choices)));
    }

    // TODO Switch to MessageFormattedExceptions
    public void submitVote(Member member, String choice)
    {
        checkIfActive();
        choice = choice.toLowerCase();
        if (!votes.containsKey(choice))
            throw new UserFriendlyException("Choice doesn't exist", "That choice doesn't exist");
        for (Member voter : votes.get(choice))
            if (member.getUser().getIdLong() == voter.getUser().getIdLong())
                throw new UserFriendlyException("MemberDAO has already voted", "You've already voted");

        votes.get(choice).add(member);
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
            throw new UserFriendlyException("Poll has already ended", "This poll's already ended");
    }

    public boolean hasEnded()
    {
        return !isActive();
    }

    private void checkIfEnded()
    {
        if (isActive())
            throw new UserFriendlyException("Poll is still active", "This poll's still active");
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
