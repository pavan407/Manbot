package com.rapidprototyping.manbot;

import com.rapidprototyping.manbot.controller.CommandListener;
import com.rapidprototyping.manbot.model.command.SimpleCommandParser;
import com.rapidprototyping.manbot.service.Service;
import com.rapidprototyping.manbot.service.impl.TriviaService;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.EventListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pavan C (pavan407)
 */
public class Manbot
{
    public static void main(String[] args) throws Exception
    {
        String token = System.getenv("MANBOT_TOKEN");
        Manbot bot = new Manbot(token);
        bot.addListener(new CommandListener(bot, new SimpleCommandParser()));
        bot.scheduleService(new TriviaService());
    }

    private final JDA jda;
    private ScheduledExecutorService serviceExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    public Manbot(String token) throws Exception
    {
        jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .buildBlocking();
    }

    public Manbot addListener(EventListener listener)
    {
        jda.addEventListener(listener);
        return this;
    }

    public Manbot scheduleService(Service service)
    {
        serviceExecutor.scheduleWithFixedDelay(() -> service.tick(this), service.initialDelay, service.intervalDelay, service.timeUnit);
        return this;
    }

    public void shutdown()
    {
        try
        {
            serviceExecutor.awaitTermination(30, TimeUnit.SECONDS);
        } catch(Exception e)
        {
            e.printStackTrace();
        } finally {
            jda.shutdown();
        }
    }

    public JDA getJDA()
    {
        return jda;
    }
}
