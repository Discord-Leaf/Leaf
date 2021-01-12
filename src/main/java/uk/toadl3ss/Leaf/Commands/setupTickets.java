package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Resources.meNoPermissions;
import uk.toadl3ss.Leaf.Resources.noPermissions;

public class setupTickets implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        if (!event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            new noPermissions().noPermissionsEmbed(event);
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            new meNoPermissions().meNoPermissionsEmbed(event);
            return;
        }
        event.getChannel().sendMessage("Ive attempted to setup tickets for you. Please not if you have tickets disabled on the dashboard then this wont work").queue();
        event.getGuild().createTextChannel("tickets").flatMap(m -> m.sendMessage("React with :white_check_mark: to create a ticket.").flatMap(r -> r.addReaction("✅"))).queue();
    }
}