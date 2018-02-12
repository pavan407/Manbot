package com.manbot.user

/**
 * @author Pavan C. (pavan407)
 */
open class MessageFormattedException @JvmOverloads constructor(
        message: String,
        val formattedMessage: String = message,
        cause: Throwable? = null
) : RuntimeException(message, cause)