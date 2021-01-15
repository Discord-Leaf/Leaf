package uk.toadl3ss.Leaf.Utillites.Console;

import uk.toadl3ss.Leaf.Utillites.Console.Actions.Restart;
import uk.toadl3ss.Leaf.Utillites.Console.Actions.Stop;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class ConsoleHandler {
    public static void ConsoleHandler(String input) throws InterruptedException {
        switch (input.toLowerCase()) {
            case "shutdown":
            case "end":
            case "stop":
                new Stop().Action(input);
                break;
            case "rs":
            case "restart":
                new Restart().Action(input);
                break;
            default:
                Logger.info("Invalid syntax" + " \"" + input + "\".");
                break;
        }
    }
}