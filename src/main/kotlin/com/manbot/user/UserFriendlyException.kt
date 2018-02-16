package com.manbot.user

/**
 * @author Pavan C. (pavan407)
 */
open class UserFriendlyException @JvmOverloads constructor(
        message: String,
        val userFriendlyMessage: String = message,
        cause: Throwable? = null
) : RuntimeException(message, cause)