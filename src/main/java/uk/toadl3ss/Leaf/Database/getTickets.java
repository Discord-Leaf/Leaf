package uk.toadl3ss.Leaf.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import uk.toadl3ss.Leaf.Utillites.Config;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class getTickets {
    public static boolean getTickets(String guildId) {
        DBObject query = new BasicDBObject("guildId", guildId);
        DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
        if (cursor.one() == null) {
            return false;
        }
        boolean tickets = (boolean) cursor.one().get("Tickets");
        return tickets;
    }
}