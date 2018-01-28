package com.rapidprototyping.manbot.model.user;

/**
 *
 * @author Pavan C (pavan407)
 */
public enum UserType
{
    LEARNER, INTERMEDIATE, ADVANCED, MENTOR, ADMIN;

    public boolean greaterThan(UserType other)
    {
        return ordinal() > other.ordinal();
    }

    public boolean lessThan(UserType other)
    {
        return ordinal() < other.ordinal();
    }
}
