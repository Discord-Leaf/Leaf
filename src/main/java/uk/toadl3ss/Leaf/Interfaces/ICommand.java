package uk.toadl3ss.Leaf.Interfaces;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
    /**
     *
     * @param event The event that gets passed when a message is sent
     * @param args The arguments of the message
     * @param prefix The prefix of the guild
     *
     */
    void Action(MessageReceivedEvent event, String[] args, String prefix);
}