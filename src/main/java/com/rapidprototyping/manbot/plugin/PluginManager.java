package com.rapidprototyping.manbot.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pavan C (pavan407)
 * @param <P>
 */
public class PluginManager<P>
{
    private List<P> plugins;

    public PluginManager(P... plugins)
    {
        this.plugins = Arrays.asList(plugins);
    }

    public PluginManager(PluginLoader<P> loader)
    {
        plugins = loader.load();
    }

    public PluginManager()
    {
        plugins = new ArrayList<>();
    }

    public PluginManager<P> addPlugins(P... plugins)
    {
        this.plugins.addAll(Arrays.asList(plugins));
        return this;
    }

    public List<P> getPlugins()
    {
        return plugins;
    }
}
