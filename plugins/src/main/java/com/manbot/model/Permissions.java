package com.manbot.model;

import com.google.common.collect.ImmutableSet;
import kotlin.jvm.functions.Function1;
import net.dv8tion.jda.core.entities.Role;

import java.util.*;

/**
 * @author Pavan C. (pavan407)
 */
public enum Permissions
{
    // Highest to lowest
    ADMIN("Admin"), MOD("Moderator"), MENTOR("Mentor"), DEV("Developer"), NORMAL;

    private final Collection<String> roleNames;

    Permissions(String... roleNames)
    {
        this.roleNames = ImmutableSet.<String>builder().addAll(Arrays.asList(roleNames)).build();
    }

    public static Function1<? super List<? extends Role>, Boolean> greaterThan(Permissions permissions)
    {
        return roles -> roles.stream().anyMatch(role -> permissions.isGreaterThan(fromRole(role)));
    }

    public static Function1<? super List<? extends Role>, Boolean> greaterThanOrEqualTo(Permissions permissions)
    {
        return roles -> roles.stream().anyMatch(role -> permissions.isGreaterThanOrEqualTo(fromRole(role)));
    }

    public static Function1<? super List<? extends Role>, Boolean> lessThan(Permissions permissions)
    {
        return roles -> roles.stream().anyMatch(role -> permissions.isLessThan(fromRole(role)));
    }

    public static Function1<? super List<? extends Role>, Boolean> lessThanOrEqualTo(Permissions permissions)
    {
        return roles -> roles.stream().anyMatch(role -> permissions.isLessThanOrEqualTo(fromRole(role)));
    }

    public static Function1<? super List<? extends Role>, Boolean> equalTo(Permissions permissions)
    {
        return roles -> roles.stream().anyMatch(role -> permissions.isEqualTo(fromRole(role)));
    }

    public static Function1<? super List<? extends Role>, Boolean> unequalTo(Permissions permissions)
    {
        return roles -> roles.stream().anyMatch(role -> permissions.isUnequalTo(fromRole(role)));
    }

    public static Permissions fromRole(Role role)
    {
//        int len = values().length;
//        Permissions[] sorted = new Permissions[len];
//        for (int i = 0; i < len; i++)
//            sorted[i] = values()[len - 1 - i];

        Optional<Permissions> optional = Arrays.stream(values()).filter(
                permissions -> permissions.getApplicableRoleNames().stream().anyMatch(
                        roleName -> role.getName().equalsIgnoreCase(roleName))
                        || role.getName().equalsIgnoreCase(permissions.toString()))
                .findFirst();
        return optional.orElse(NORMAL);
    }

    public boolean isGreaterThan(Permissions other)
    {
        return compareTo(other) > 0;
    }

    public boolean isGreaterThanOrEqualTo(Permissions other)
    {
        return compareTo(other) >= 0;
    }

    public boolean isLessThan(Permissions other)
    {
        return compareTo(other) < 0;
    }

    public boolean isLessThanOrEqualTo(Permissions other)
    {
        return compareTo(other) <= 0;
    }

    public boolean isEqualTo(Permissions other)
    {
        return compareTo(other) == 0;
    }

    public boolean isUnequalTo(Permissions other)
    {
        return compareTo(other) != 0;
    }

    public Collection<String> getApplicableRoleNames()
    {
        return roleNames;
    }
}
