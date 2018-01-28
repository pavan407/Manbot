package com.rapidprototyping.manbot.service.impl;

import com.rapidprototyping.manbot.Manbot;
import com.rapidprototyping.manbot.service.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Proof of concept for {@link Service}.
 *
 * @author Pavan C (pavan407)
 */
public class TriviaService extends Service
{
    private String[] trivia = {
            "Remember to ask for help when you need it!",
            "If you need help using the bot, type use the !help command.",
            "Please treat all members fairly and with the respect you want them to treat you with.",
            "You can visit our website at http://learnandcode.it/",
            "PROJECT CONTRIBUTORS, be sure to rely on GitHub for projects updates and not Discord!"
    };
    private Random rng = new Random();

    public TriviaService()
    {
        super(2, 5, TimeUnit.MINUTES);
    }

    @Override
    public void tick(Manbot bot)
    {
        int triviaId;
        while (true)
        {
            triviaId = rng.nextInt(trivia.length);
            if (triviaId >= trivia.length)
                continue;
            break;
        }
        bot.getJDA().getTextChannelById(406595900270444545L).sendMessage("[Trivia] " + trivia[triviaId]).queue();
    }
}
