package uk.toadl3ss.Leaf.Events;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class messageReactionAddComplete extends ListenerAdapter {
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (!event.getChannel().getName().startsWith("ticket-")) {
            return;
        }
        if (event.getMember().getUser().equals(event.getJDA().getSelfUser())) {
            return;
        }
        if (!event.getReactionEmote().getName().equals("❌")) {
            return;
        }
        event.getReaction().removeReaction(event.getUser()).queue();
        event.getChannel().delete().queue();
        new messageReactionAddTicket().hasTicket.remove(event.getMember(), true);
    }
}