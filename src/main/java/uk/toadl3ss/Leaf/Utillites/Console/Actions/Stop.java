package uk.toadl3ss.Leaf.Utillites.Console.Actions;

import uk.toadl3ss.Leaf.Interfaces.IConsole;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class Stop implements IConsole {
    @Override
    public void Action(String input) {
        Logger.info("System exiting.");
        System.exit(0);
    }
}