package uk.toadl3ss.Leaf.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import uk.toadl3ss.Leaf.Database.getPrefix;

public class CommandHandler extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        final String guildId = event.getGuild().getId();
        final String guildIconId = event.getGuild().getIconId();
        final String guildName = event.getGuild().getName();
        final String prefix = getPrefix.getPrefix(guildId, guildName, guildIconId);
        String[] args = event.getMessage().getContentRaw().split(" ");
        // If the prefix is @leaf
        String mention = "<@!" + event.getJDA().getSelfUser().getId() + ">";
        if (args[0].startsWith(mention)) {
            String command = args[0].replaceFirst("^" + mention, "");
            try {
                Commands.Commands(event, command, args, mention);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        // Checking if the messages starts with the guilds prefix
        if (args[0].startsWith(prefix)) {
            String command = args[0].replaceFirst("^" + prefix, "");
            try {
                Commands.Commands(event, command, args, prefix);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}