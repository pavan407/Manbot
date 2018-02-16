package com.manbot.task

import com.manbot.Manbot
import java.util.concurrent.TimeUnit

/**
 * @author Pavan C. (pavan407)
 */
abstract class Service @JvmOverloads constructor(
        val intervalDelay: Long,
        val initialDelay: Long = 0,
        val timeUnit: TimeUnit = TimeUnit.SECONDS
) : Task()
{
    override fun onInit()
    {
        Manbot.scheduleService(this)
    }
}
