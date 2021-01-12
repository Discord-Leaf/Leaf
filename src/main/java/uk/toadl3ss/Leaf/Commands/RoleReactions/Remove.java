package uk.toadl3ss.Leaf.Commands.RoleReactions;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
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

public class Remove implements ICommand {
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
            event.getChannel().sendMessage("Please provide a role id.").queue();
            return;
        }
        String channel = args[1];
        String role = args[2];
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
        try {
            DBObject query = new BasicDBObject("guildId", event.getGuild().getId());
            DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
            BasicDBList roleReactions = (BasicDBList) cursor.one().get("RoleReactions");
            for (Object roleReaction : roleReactions) {
                BasicDBObject parsedRoleReaction = (BasicDBObject) JSON.parse(roleReaction.toString());
                if (channel.equals(parsedRoleReaction.get("channelId").toString())) {
                    if (role.equals(parsedRoleReaction.get("roleId").toString())) {
                        BasicDBObject update = new BasicDBObject("RoleReactions", roleReaction);
                        Main.guild.update(query, new BasicDBObject("$pull", update));
                        event.getChannel().sendMessage("Successfully deleted the role reaction.").queue();
                        return;
                    }
                    return;
                }
                return;
            }
        } catch (Exception e) {
            Logger.error("Error deleting a role reaction.");
            event.getChannel().sendMessage("An error occured whilst deleting this role reaction.").queue();
        }
    }
}