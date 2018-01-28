package com.rapidprototyping.manbot.controller;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Pavan C (pavan407)
 */
public class NewUserListener extends ListenerAdapter
{
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event)
    {
        User newUser = event.getUser();
        event.getGuild().getTextChannelById(403187432159313923L).sendMessage("Please welcome " +
                newUser.getAsMention() + " to the guild!").queue();
    }
}
