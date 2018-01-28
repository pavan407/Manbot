package com.rapidprototyping.manbot.service;

import com.rapidprototyping.manbot.Manbot;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pavan C (pavan407)
 */
public abstract class Service
{
    public final long initialDelay;
    public final long intervalDelay;
    public final TimeUnit timeUnit;

    protected Service(long initialDelaySeconds, long intervalDelaySeconds)
    {
        this(initialDelaySeconds, intervalDelaySeconds, TimeUnit.SECONDS);
    }

    protected Service(long initialDelay, long intervalDelay, TimeUnit unit)
    {
        this.initialDelay = initialDelay;
        this.intervalDelay = intervalDelay;
        this.timeUnit = unit;
    }

    public abstract void tick(Manbot bot);
}
