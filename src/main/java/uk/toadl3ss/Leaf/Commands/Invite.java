package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;

public class Invite implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        final TextChannel channel = event.getTextChannel();
        channel.createInvite()
                .flatMap(invite -> channel.sendMessage(event.getGuild().getName() + "'s Invites:" + " " + invite.getUrl()))
                .queue();
    }
}