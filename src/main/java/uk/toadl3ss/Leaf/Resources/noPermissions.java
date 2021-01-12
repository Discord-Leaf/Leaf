package uk.toadl3ss.Leaf.Resources;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class noPermissions {
    public void noPermissionsEmbed(MessageReceivedEvent event) {
        final MessageChannel channel = event.getChannel();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription("You do not have the required permission to preform this action.");
        channel.sendMessage(embed.build()).queue();
    }
}