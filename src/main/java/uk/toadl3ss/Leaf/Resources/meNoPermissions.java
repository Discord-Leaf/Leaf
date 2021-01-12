package uk.toadl3ss.Leaf.Resources;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class meNoPermissions {
    public void meNoPermissionsEmbed(MessageReceivedEvent event) {
        final MessageChannel channel = event.getChannel();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription("I dont have the required permission to perform this action.");
        channel.sendMessage(embed.build()).queue();
    }
}