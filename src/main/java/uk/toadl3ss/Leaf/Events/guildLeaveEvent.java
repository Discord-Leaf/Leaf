package uk.toadl3ss.Leaf.Events;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class guildLeaveEvent extends ListenerAdapter {
    public void onGuildLeave(GuildLeaveEvent event) {
        try {
            DBObject query = new BasicDBObject("guildId", event.getGuild().getId());
            DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
            if (cursor.one() == null) {
                return;
            }
            uk.toadl3ss.Leaf.Database.Main.guild.findAndRemove(query); // Once the bot is removed from the guild we will drop the guilds document
        } catch (Error e) {
            Logger.error("Unable to remove a guilds database.");
        }
    }
}