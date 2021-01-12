package uk.toadl3ss.Leaf.Commands.RoleReactions;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Database.Main;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Resources.noPermissions;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class Add implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        if (!event.getMember().hasPermission(Permission.MANAGE_PERMISSIONS)) {
            new noPermissions().noPermissionsEmbed(event);
            return;
        }
        if (args.length < 2) {
            event.getChannel().sendMessage("Please provide a channel id.").queue();
            return;
        }
        if (args.length < 3) {
            event.getChannel().sendMessage("Please provide a message id.").queue();
            return;
        }
        if (args.length < 4) {
            event.getChannel().sendMessage("Please provide a role id.").queue();
            return;
        }
        String channel = args[1];
        String message = args[2];
        String role = args[3];
        Role fetchedRole = event.getGuild().getRoleById(role);
        if (fetchedRole == null) {
            event.getChannel().sendMessage("Please provide a valid role id.").queue();
            return;
        }
        TextChannel fetchedChannel = event.getGuild().getTextChannelById(channel);
        if (fetchedChannel == null) {
            event.getChannel().sendMessage("Please provide a valid channel id.").queue();
            return;
        }
//        Message fetchedMessage = fetchedChannel.getHistory().getMessageById(message);
//        if (fetchedMessage == null) {
//            event.getChannel().sendMessage("Please provide an existing message. The id that you have provided isn't a real message.").queue();
//            return;
//        }
        try {
            DBObject query = new BasicDBObject("guildId", event.getGuild().getId());
            String roleReaction = ("{\"roleId\": \"" + role + "\", \"channelId\": \"" + channel + "\", \"messageId\": \"" + message + "\"}");
            BasicDBObject parsedRoleReaction = (BasicDBObject) JSON.parse(roleReaction);
            DBObject arrayRoleReaction = new BasicDBObject("RoleReactions", parsedRoleReaction);
            DBObject updateQuery = new BasicDBObject("$push", arrayRoleReaction);
            Main.guild.update(query, updateQuery);
            event.getChannel().sendMessage("Successfully created the role reaction!").queue();
        } catch (Exception e) {
            Logger.error("Error creating a role reaction.");
            event.getChannel().sendMessage("An error occured whilst creating this role reaction.").queue();
        }
    }
}