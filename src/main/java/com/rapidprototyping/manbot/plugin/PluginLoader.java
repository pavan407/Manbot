package com.rapidprototyping.manbot.plugin;

import java.util.List;

/**
 *
 * @author Pavan C (pavan407)
 * @param <P>
 */
public interface PluginLoader<P>
{
    List<P> load();
}
