package com.manbot.model;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.managers.RoleManager;
import net.dv8tion.jda.core.managers.RoleManagerUpdatable;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.core.requests.restaction.RoleAction;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Pavan C. (pavan407)
 */
public class PermissionsTest
{
    @Test
    public void comparePermissions()
    {
        System.out.println(Permissions.unequalTo(Permissions.ADMIN).invoke(Arrays.asList(new Role()
        {
            @Override
            public long getIdLong()
            {
                return 0;
            }

            @Override
            public String getAsMention()
            {
                return null;
            }

            @Override
            public int compareTo(@NotNull Role o)
            {
                return 0;
            }

            @Override
            public int getPosition()
            {
                return 0;
            }

            @Override
            public int getPositionRaw()
            {
                return 0;
            }

            @Override
            public String getName()
            {
                return "mod";
            }

            @Override
            public boolean isManaged()
            {
                return false;
            }

            @Override
            public boolean isHoisted()
            {
                return false;
            }

            @Override
            public boolean isMentionable()
            {
                return false;
            }

            @Override
            public long getPermissionsRaw()
            {
                return 0;
            }

            @Override
            public Color getColor()
            {
                return null;
            }

            @Override
            public boolean isPublicRole()
            {
                return false;
            }

            @Override
            public boolean canInteract(Role role)
            {
                return false;
            }

            @Override
            public Guild getGuild()
            {
                return null;
            }

            @Override
            public List<Permission> getPermissions()
            {
                return null;
            }

            @Override
            public boolean hasPermission(Permission... permissions)
            {
                return false;
            }

            @Override
            public boolean hasPermission(Collection<Permission> permissions)
            {
                return false;
            }

            @Override
            public boolean hasPermission(Channel channel, Permission... permissions)
            {
                return false;
            }

            @Override
            public boolean hasPermission(Channel channel, Collection<Permission> permissions)
            {
                return false;
            }

            @Override
            public RoleAction createCopy(Guild guild)
            {
                return null;
            }

            @Override
            public RoleManager getManager()
            {
                return null;
            }

            @Override
            public RoleManagerUpdatable getManagerUpdatable()
            {
                return null;
            }

            @Override
            public AuditableRestAction<Void> delete()
            {
                return null;
            }

            @Override
            public JDA getJDA()
            {
                return null;
            }
        })));
    }
}
