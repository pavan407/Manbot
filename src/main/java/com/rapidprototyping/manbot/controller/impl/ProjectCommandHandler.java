package com.rapidprototyping.manbot.controller.impl;

import com.rapidprototyping.manbot.controller.CommandContext;
import com.rapidprototyping.manbot.controller.CommandHandler;
import com.rapidprototyping.manbot.model.project.Project;
import com.rapidprototyping.manbot.model.project.ProjectManager;

public class ProjectCommandHandler implements CommandHandler
{
    @Override
    public String getName()
    {
        return "projects";
    }

    @Override
    public void handle(CommandContext ctx)
    {
        StringBuilder projects = new StringBuilder();
        for (Project project : ProjectManager.getProjects())
            projects = projects.append("- ").append(project.getName()).append(": ").append(project.getDescription()).append("\n");
        ctx.getChannel().sendMessage(ctx.getSender().getAsMention() + ", here's a list of all currently active projects:\n" +
                projects).queue();
    }

    @Override
    public String getDescription()
    {
        return "Retrieves a list of all the currently active projects";
    }
}
