package uk.toadl3ss.Leaf.Utillites.Console.Actions;

import uk.toadl3ss.Leaf.Interfaces.IConsole;
import uk.toadl3ss.Leaf.Main;
import uk.toadl3ss.Leaf.Utillites.Info;
import uk.toadl3ss.Leaf.Utillites.Logger;
import uk.toadl3ss.Leaf.Utillites.Vanity;

public class Restart implements IConsole {
    @Override
    public void Action(String input) {
        Logger.info("Leaf - Restarting");
        Vanity.printVainity();
        Info.printInfo(Main.version);
        Main.jda.restart();
    }
}