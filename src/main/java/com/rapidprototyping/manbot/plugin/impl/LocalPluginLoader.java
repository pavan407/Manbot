package com.rapidprototyping.manbot.plugin.impl;

import com.rapidprototyping.manbot.plugin.PluginLoader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Pavan C (pavan407)
 * @param <P>
 */
public class LocalPluginLoader<P> implements PluginLoader<P>
{
    private Path pluginDirectory;

    public LocalPluginLoader(Path pluginDirectory)
    {
        this.pluginDirectory = pluginDirectory;
    }

    public LocalPluginLoader(String pluginDirectory)
    {
        this(Paths.get(pluginDirectory));
    }

    @Override
    public List<P> load()
    {

        return null;
    }
}
