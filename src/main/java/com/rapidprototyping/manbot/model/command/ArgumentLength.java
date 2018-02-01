package com.rapidprototyping.manbot.model.command;

/**
 *
 * @author Pavan C (pavan407)
 */
@FunctionalInterface
public interface ArgumentLength
{
    static ArgumentLength anyLength()
    {
        return (len) -> true;
    }

    static ArgumentLength fixed(int fix)
    {
        return (len) -> len == fix;
    }

    static ArgumentLength inbetween(int min, int max)
    {
        return (len) -> len >= min && len <= max;
    }

    static ArgumentLength greaterThan(int max)
    {
        return (len) -> len > max;
    }

    static ArgumentLength lessThan(int max)
    {
        return (len) -> len < max;
    }

    boolean conformsByPolicy(int length);
}
