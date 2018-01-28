package com.rapidprototyping.manbot.controller.impl;

import com.rapidprototyping.manbot.controller.CommandContext;
import com.rapidprototyping.manbot.controller.CommandHandler;
import com.rapidprototyping.manbot.model.command.ArgumentLength;
import com.rapidprototyping.manbot.model.project.Project;
import com.rapidprototyping.manbot.model.project.ProjectManager;

public class ProjectOperationCommandHelper implements CommandHandler
{
    @Override
    public String getName()
    {
        return "project";
    }

    @Override
    public void handle(CommandContext ctx)
    {
        String op = ctx.getCommand().getArgs().get(0);
        switch(op)
        {
            case "create":
            {
                String name = ctx.getCommand().getArgs().get(0);
                String desc = ctx.getCommand().getArgs().get(1);
                String lang = ctx.getCommand().getArgs().get(2);

                ProjectManager.addProject(new Project(name, desc));
                ctx.getChannel().sendMessage(ctx.getSender().getAsMention() + ", project added!").queue();
            }
        }
    }

    @Override
    public ArgumentLength getArgumentLength()
    {
        return ArgumentLength.greaterThan(1);
    }

    @Override
    public String getUsage()
    {
        return "project create [name] [desc] [lang]";
    }

    @Override
    public String getDescription()
    {
        return "Project operations";
    }
}
