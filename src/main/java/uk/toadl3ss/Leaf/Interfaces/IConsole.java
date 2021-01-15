package uk.toadl3ss.Leaf.Interfaces;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface IConsole {
    /**
     *
     * @param input The console input
     */
    void Action(String input);
}