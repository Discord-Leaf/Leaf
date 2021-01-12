package uk.toadl3ss.Leaf.Utillites;

import java.io.File;
import java.io.IOException;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.simpleyaml.exceptions.InvalidConfigurationException;

public class Config {
    private File config;
    public static Config INS;
    public String Database;
    public String Prefix;
    public String Token;
    public String ActivityType;
    public String ActivityString;
    public String Name;

    private Config(String file) {
        config = new File(file);
        initConfig();
    }

    public static void init(String file) {
        INS = new Config(file);
    }

    private void initConfig() {
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(this.config);
        } catch (InvalidConfigurationException | IOException e) {
            Logger.error("Invalid application.yml");
            return;
        }
        this.Database = config.getString("database.url");
        this.Name = config.getString("database.name");
        this.Token = config.getString("bot.token");
        this.Prefix = config.getString("bot.prefix");
        this.ActivityString = config.getString("bot.activity.string");
        this.ActivityType = config.getString("bot.activity.type");
    }

    //Fetching the bots token
    public String getToken() {
        return Token;
    }

    //Fetching the default prefix
    public String getPrefix() {
        return Prefix;
    }

    //Fetching the database connect url
    public String getDatabase() {
        return Database;
    }

    //Fetching the bots activity type
    public String getActivityType() {
        return ActivityType;
    }

    //Fetching the bots activity string
    public String getActivityString() {
        return ActivityString;
    }

    //Fetching the bots database name
    public String getDatabaseName() {
        return Name;
    }
}