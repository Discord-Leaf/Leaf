package uk.toadl3ss.Leaf.Utillites.Console.Actions;

import uk.toadl3ss.Leaf.Interfaces.IConsole;
import uk.toadl3ss.Leaf.Main;
import uk.toadl3ss.Leaf.Utillites.Logger;

import java.util.concurrent.TimeUnit;

public class Stop implements IConsole {
    @Override
    public void Action(String input) throws InterruptedException {
        Logger.info("System exiting.");
        Main.jda.shutdown();
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }
}