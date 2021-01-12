package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Database.getPrefix;
import uk.toadl3ss.Leaf.Interfaces.ICommand;

public class Prefix implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        String guildPrefix = getPrefix.getPrefix(event.getGuild().getId(), event.getGuild().getName(), event.getGuild().getIconId());
        event.getChannel().sendMessage("This guilds prefix is:" + " " + guildPrefix).queue();
    }
}