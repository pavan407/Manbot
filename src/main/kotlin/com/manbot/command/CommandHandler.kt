package com.manbot.command

import com.manbot.plugin.Plugin
import net.dv8tion.jda.core.entities.Role

/**
 * @author Pavan C. (pavan407)
 */
abstract class CommandHandler @JvmOverloads constructor(
        val name: String,
        val description: String = "N/A",
        val requiredArgumentAmount: (Int) -> Boolean = ArgumentPolicies.anyLength(),
        val usage: String = "N/A",
        val requiredPermissions: (List<Role>) -> Boolean = { true }
) : Plugin
{
    override fun onInit() = CommandHandlerCoordinator.applyCoordination(this)

    abstract fun handle(event: CommandEvent)
}