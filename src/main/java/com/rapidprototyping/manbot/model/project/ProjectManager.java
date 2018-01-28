package com.rapidprototyping.manbot.model.project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pavan C (pavan407)
 */
public class ProjectManager
{
    private static Map<String, Project> projects = new HashMap<>();

    static
    {
        Project manbot = new Project("Manbot", "A discord chat bot, the technology behind me!");
        projects.put("manbot", manbot);
    }

    public static Project getProject(String name)
    {
        return projects.get(name);
    }

    public static Collection<Project> getProjects()
    {
        return projects.values();
    }

    public static void addProject(Project p)
    {
        projects.put(p.getName(), p);
    }
}
