package uk.toadl3ss.Leaf.Events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.w3c.dom.Text;
import uk.toadl3ss.Leaf.Database.getTickets;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public class messageReactionAddTicket extends ListenerAdapter {
    HashMap<Member, Boolean> hasTicket = new HashMap<>();
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (!event.getChannel().getName().equals("tickets")) {
            return;
        }
        if (event.getMember().getUser().equals(event.getJDA().getSelfUser())) {
            return;
        }
        if (!event.getReactionEmote().getName().equals("✅")) {
            return;
        }
        boolean tickets = getTickets.getTickets(event.getGuild().getId());
        if (!tickets) {
            return;
        }
        event.getReaction().removeReaction(event.getUser()).queue();
        Boolean userHasTicket = hasTicket.get(event.getMember());
        if (userHasTicket != null) {
            event.getMember().getUser().openPrivateChannel().queue((privateChannel -> {
                privateChannel.sendMessage("You already have an open ticket").queue();
            }));
            if (userHasTicket) {
                return;
            }
            return;
        }
        hasTicket.put(event.getMember(), true);
        event.getGuild().createTextChannel("ticket-" + event.getMember().getEffectiveName())
                .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .flatMap(textChannel -> textChannel.sendMessage("React with :x: to complete the ticket.").flatMap(message -> message.addReaction("❌")))
                .queue()
        ;
    }
}