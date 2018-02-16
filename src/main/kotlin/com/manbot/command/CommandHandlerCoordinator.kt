package com.manbot.command

import com.manbot.Manbot
import com.manbot.event.EventSubscriber
import com.manbot.user.UserFriendlyException

/**
 * @author Pavan C. (pavan407)
 */
object CommandHandlerCoordinator : EventSubscriber<CommandEvent>, Iterable<CommandHandler>
{
    private val handlers = HashMap<String, CommandHandler>()

    init
    {
        Manbot.eventProvider.provideSubscriber(this)
    }

    fun applyCoordination(handler: CommandHandler)
    {
        handlers.putIfAbsent(handler.name, handler)
    }

    fun removeCoordination(handler: CommandHandler) = handlers.remove(handler.name, handler)

    override fun subscribe(event: CommandEvent)
    {
        // TODO This is temporary (Removes the "!" from the command)
        val nameMod = event.command.name.substring(1)

        val handler = handlers[nameMod]
        if (handler == null)
            event.channel.sendMessage("Hey ${event.member.asMention}, the requested command wasn't found.").queue()
        else if (!handler.requiredPermissions(event.member.roles))
            return
        else if (!handler.requiredArgumentAmount(event.command.amountOfArguments))
            event.channel.sendMessage("Hmm... You're missing some arguments  ${event.member.asMention}.").queue()
        else
            try
            {
                handler.handle(event)
            } catch(e: UserFriendlyException)
            {
                event.channel.sendMessage("${e.userFriendlyMessage} ${event.member.asMention}!").queue()
            } catch(e: Exception)
            {
                event.channel.sendMessage("There was an error processing your input ${event.member.asMention}.").queue()
                // TODO Log the error
                e.printStackTrace()
            }
    }

    override fun iterator() = handlers.values.iterator()
}