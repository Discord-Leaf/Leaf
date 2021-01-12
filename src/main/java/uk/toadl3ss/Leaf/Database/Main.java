package uk.toadl3ss.Leaf.Database;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import uk.toadl3ss.Leaf.Utillites.Config;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class Main {
    public static MongoClient mongoClient;
    public static DBCollection guild;
    public static DBCollection user;
    public static DB database;
    public static void main() {
        Logging.updateLogging();
        // Logging into the database
        try {
            mongoClient = new MongoClient(new MongoClientURI(Config.INS.getDatabase()));
            database = mongoClient.getDB(Config.INS.getDatabaseName());
            guild = database.getCollection("guilds");
            user = database.getCollection("userWarns");
            Logger.info("Connected to the database.");
        } catch (Exception e) {
            Logger.error("Error logging into the database.");
        }
    }
}