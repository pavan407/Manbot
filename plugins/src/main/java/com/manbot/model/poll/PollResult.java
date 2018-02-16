package com.manbot.model.poll;

import com.google.common.collect.ImmutableSet;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;

import java.util.Collection;

/**
 * @author Pavan C. (pavan407)
 */
public final class PollResult implements Comparable<PollResult>
{
    private final String choice;
    private final Collection<Member> voters;

    PollResult(String choice, Collection<Member> voters)
    {
        this.choice = choice;
        this.voters = ImmutableSet.copyOf(voters);
    }

    @Override
    public String toString()
    {
        return choice + " - " + getAmountOfVoters();
    }

    @Override
    public int compareTo(PollResult other)
    {
        if (getAmountOfVoters() == other.getAmountOfVoters())
            return 0;
        return getAmountOfVoters() > other.getAmountOfVoters() ? 1 : -1;
    }

    public String getChoice()
    {
        return choice;
    }

    public Collection<Member> getVoters()
    {
        return voters;
    }

    public int getAmountOfVoters()
    {
        return getVoters().size();
    }
}
