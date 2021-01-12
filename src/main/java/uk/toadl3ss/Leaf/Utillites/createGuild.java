package uk.toadl3ss.Leaf.Utillites;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class createGuild {
    public void createGuild(String guildId, String guildName, String guildIcon) {
        DBObject query = new BasicDBObject("guildId", guildId);
        DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
        if (cursor.one() == null) {
            DBObject guild = new BasicDBObject("guildId", guildId)
                    .append("guildPrefix", Config.INS.getPrefix())
                    .append("guildName", guildName)
                    .append("Icon", guildIcon)
                    .append("Music", true)
                    .append("Tickets", false)
                    .append("Leveling", false)
                    ;
            uk.toadl3ss.Leaf.Database.Main.guild.insert(guild);
            return;
        }
        Logger.warn("There was already a database for the new guild. The database didnt get removed correctly.");
    }
}