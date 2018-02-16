package com.manbot.model.poll;

import com.google.common.collect.ImmutableMap;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Pavan C. (pavan407)
 */
public final class PollResults implements Iterable<PollResult>
{
    private final ImmutableMap<String, PollResult> results;

    PollResults(ImmutableMap<String, Set<Member>> votes)
    {
        ImmutableMap.Builder<String, PollResult> builder = ImmutableMap.builderWithExpectedSize(votes.keySet().size());
        votes.forEach((choice, voters) -> builder.put(choice, new PollResult(choice, voters)));
        results = builder.build();
    }

    @Override
    public String toString()
    {
        StringBuilder tally = new StringBuilder();
        getAll().forEach(result -> tally.append(result).append("\t"));
        return tally.toString();
    }

    @Override
    public Iterator<PollResult> iterator()
    {
        return getAll().iterator();
    }

    public PollResult get(String choice)
    {
        return results.get(choice);
    }

    public Collection<PollResult> getAll()
    {
        return results.values();
    }

    public PollResult getMostVotedChoice()
    {
        return Collections.max(getAll());
    }

    public PollResult getLeastVotedChoice()
    {
        return Collections.min(getAll());
    }
}
