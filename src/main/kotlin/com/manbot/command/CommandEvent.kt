package com.manbot.command

import com.manbot.event.Event
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.MessageChannel

/**
 * @author Pavan C. (pavan407)
 */
class CommandEvent(
        val command: Command,
        val member: Member,
        val channel: MessageChannel
) : Event
