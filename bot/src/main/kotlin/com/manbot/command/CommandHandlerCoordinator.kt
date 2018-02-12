package com.manbot.command

import com.manbot.Manbot
import com.manbot.event.EventSubscriber
import com.manbot.user.MessageFormattedException

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
        if (handler != null && nameMod.equals(handler.name, true)
                // TODO Check user type!
                && handler.argumentAmount(event.command.amountOfArguments))
            try
            {

                handler.handle(event)
            } catch(e: MessageFormattedException)
            {
                event.channel.sendMessage("${e.formattedMessage} ${event.user.asMention}!")
            } catch(e: Exception)
            {
                // TODO Log the error
                e.printStackTrace()
            }
        else
            event.channel.sendMessage("Hey ${event.user.asMention}, the requested command wasn't found.").queue()
    }

    override fun iterator() = handlers.values.iterator()
}