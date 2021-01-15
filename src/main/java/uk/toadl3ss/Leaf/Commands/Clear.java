package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Resources.noPermissions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Clear implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        if (!event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            new noPermissions().noPermissionsEmbed(event);
            return;
        }
        if (args.length < 2) {
            event.getChannel().sendMessage("Please provide an amount of messages to delete in the range of `1-100`.").queue();
            return;
        }
        if (Integer.parseInt(args[1]) > 100) {
            event.getChannel().sendMessage("Make sure that your range is `1-100`.").queue();
            return;
        }
        try {
            final MessageChannel channel = event.getChannel();
            MessageHistory history = new MessageHistory(channel);
            history.retrievePast(Integer.parseInt(args[1])).queue(channel::purgeMessages);
            channel.sendMessage("Successfully deleted" + " " + args[1] + " " + "messages.")
                    .delay(6, TimeUnit.SECONDS)
                    .flatMap(Message::delete)
                    .queue();
        } catch (IllegalArgumentException e) {
            event.getChannel().sendMessage("I cant delete messages older than 2 weeks!").queue();
        }
    }
}