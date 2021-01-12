package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;

public class Avatar implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        if (args.length < 2) {
            event.getChannel().sendMessage("Please provide a user").queue();
            return;
        }
        if (event.getMessage().getMentionedUsers().isEmpty()) {
            event.getChannel().sendMessage("Please provide a valid member!").queue();
            return;
        }
        try {
            final User user = event.getMessage().getMentionedUsers().get(0);
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(0x62bd00);
            embed.setTitle(user.getName() + "'s avatar.");
            embed.setImage(user.getAvatarUrl());
            embed.setAuthor(event.getJDA().getSelfUser().getName(), "https://leaf-bot.xyz", event.getJDA().getSelfUser().getAvatarUrl());
            event.getChannel().sendMessage(embed.build()).queue();
        } catch (Error e) {
            event.getChannel().sendMessage("An error occured.").queue();
        }
    }
}