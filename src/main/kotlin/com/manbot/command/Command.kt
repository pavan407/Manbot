package com.manbot.command

import com.manbot.user.UserFriendlyException
import java.util.ArrayList

/**
 * @author Pavan C. (pavan407)
 */
class Command(val name: String, val args: List<String>)
{
    private var index = 0

    val nextArgument: String
        @Throws(UserFriendlyException::class)
        get() =
            if (hasMoreArguments)
                args[index++]
            else
                throw UserFriendlyException("Index out of bounds",
                        "Looks like you need to provide some more arguments, use ![command] -help for more info")

    @Throws(UserFriendlyException::class)
    fun getNextArgs(amount: Int): List<String>
    {
        @Suppress("NAME_SHADOWING")
        var amount = amount
        val args = ArrayList<String>(amount)
        while (amount > 0)
        {
            args += nextArgument
            amount--
        }
        return args
    }

    val remainingArguments: List<String>
        @Throws(UserFriendlyException::class)
        get() = getNextArgs(amountOfArguments - index)

    val amountOfArguments: Int
        get() = args.size

    val hasMoreArguments
        get() = index < amountOfArguments

    override fun toString(): String
    {
        val cmdStr = StringBuilder("!$name")
        args.forEach({ arg -> cmdStr.append(" $arg") })
        return cmdStr.toString()
    }
}
