package uk.toadl3ss.Leaf.Commands.Moderation;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Resources.meNoPermissions;
import uk.toadl3ss.Leaf.Resources.noPermissions;

public class Ban implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        if (!event.getMember().hasPermission(Permission.getFromOffset(0x00000004))) {
            new noPermissions().noPermissionsEmbed(event);
            return;
        }
        if (args.length < 2) {
            event.getChannel().sendMessage("Please provide a member to ban!").queue();
            return;
        }
        if (event.getMessage().getMentionedUsers().isEmpty()) {
            event.getChannel().sendMessage("Please provide a valid member to ban!").queue();
            return;
        }
        final Member target = event.getMessage().getMentionedMembers().get(0);
        final Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            new meNoPermissions().meNoPermissionsEmbed(event);
            return;
        }
        event.getGuild()
                .ban(target, 1)
                .queue();
        event.getChannel().sendMessage("Banned" + " " + target.getNickname());
    }
}