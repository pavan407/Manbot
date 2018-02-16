# Manbot
An easily extensible [Discord](https://discordapp.com/ "Discord") bot.

### Plugin support
Currently, Manbot searches for plugins (class files) contained in the *plugins* project build output.

#### Creating a plugin
You can easily create a plugin class that will be dynamically loaded upon runtime by implementing the ```Plugin``` interface like so:
```java
import com.manbot.plugin.Plugin;

public class MyPlugin implements Plugin
{
	@Override
	public void onInit()
	{
		System.out.println("I've been given life!");
	}
}
```
You can also extend off some wrapper classes for a solving a particular problem, which is often more convenient. Handling commands for example:
```java
import com.manbot.command.*;
import com.manbot.user.UserFriendlyException;

public class MyCommandHandler extends CommandHandler
{
    public MyCommandHandler()
    {
        super("mycmd", 
                "Description here", 
                ArgumentPolicies.fixed(1), 
                "[yourName]");
    }

    @Override
    public void handle(CommandEvent event) throws UserFriendlyException
    {
        String name = event.getCommand().getNextArgument();

        if (name.equals("pavan"))
            throw new UserFriendlyException("Name is pavan", "You're not allowed to use this command");

        event.getChannel().sendMessage("Hello " + name + ", " + event.getMember().getAsMention()).queue();
    }
}
```
Or performing a task every so often:
```java
import com.manbot.task.Service;

import java.util.concurrent.TimeUnit;

public class MyService extends Service
{
    public MyService()
    {
        super(1, 0, TimeUnit.MINUTES);
    }

    @Override
    public void run()
    {
        // This will be executed once every minute
        System.out.println("Tick tock...");
    }
}
```
