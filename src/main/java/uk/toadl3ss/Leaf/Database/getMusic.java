package uk.toadl3ss.Leaf.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class getMusic {
    public static boolean getMusic(String guildId) {
        DBObject query = new BasicDBObject("guildId", guildId);
        DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
        if (cursor.one() == null) {
            return false;
        }
        boolean music = (boolean) cursor.one().get("Music");
        return music;
    }
}