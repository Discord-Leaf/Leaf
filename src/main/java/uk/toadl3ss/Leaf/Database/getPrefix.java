package uk.toadl3ss.Leaf.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import uk.toadl3ss.Leaf.Utillites.Config;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class getPrefix {
    public static String getPrefix(String guildId, String name, String icon) {
        DBObject query = new BasicDBObject("guildId", guildId);
        DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
        if (cursor.one() == null) {
            Logger.warn("Initial database creation failed. Had to create a database.");
            DBObject guild = new BasicDBObject("guildId", guildId)
                    .append("guildPrefix", Config.INS.getPrefix())
                    .append("guildName", name)
                    .append("Icon", icon)
                    .append("Music", true)
                    .append("Tickets", false)
                    .append("Leveling", false)
            ;
            uk.toadl3ss.Leaf.Database.Main.guild.insert(guild);
            return Config.INS.getPrefix();
        }
        String prefix = (String) cursor.one().get("guildPrefix");
        return prefix;
    }
}