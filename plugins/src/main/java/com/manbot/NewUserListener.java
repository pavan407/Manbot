package com.manbot;

import com.manbot.plugin.Plugin;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Pavan C. (pavan407)
 */
public class NewUserListener extends ListenerAdapter implements Plugin
{
    @Override
    public void onInit()
    {
        Manbot.registerListeners(this);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event)
    {
        event.getGuild().getTextChannelById(403187432159313923L).sendMessage("Everyone please welcome "
                + event.getUser().getAsMention() + " to the family!").queue();
    }
}
