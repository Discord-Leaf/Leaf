package uk.toadl3ss.Leaf.Events;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class messageReactionAddRR extends ListenerAdapter {
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        DBObject query = new BasicDBObject("guildId", event.getGuild().getId());
        DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
        BasicDBList roleReactions = (BasicDBList) cursor.one().get("RoleReactions");
        for (Object roleReaction : roleReactions) {
            BasicDBObject parsedRoleReaction = (BasicDBObject) JSON.parse(roleReaction.toString());
            if (event.getChannel().getId().startsWith(parsedRoleReaction.get("channelId").toString())) {
                if (event.getMessageId().startsWith(parsedRoleReaction.get("messageId").toString())) {
                    try {
                        Role fetchedRole = event.getGuild().getRoleById(parsedRoleReaction.get("roleId").toString());
                        if (fetchedRole == null) {
                            return;
                        }
                        event.getGuild().addRoleToMember(event.getMember(), fetchedRole).queue();
                    } catch (Exception e) {
                        Logger.info("An error occured whilst granting a role.");
                    }
                    return;
                }
                return;
            }
            return;
        }
    }
}