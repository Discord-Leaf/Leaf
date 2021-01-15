package uk.toadl3ss.Leaf.Utillites;

import java.io.File;
import java.io.IOException;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.simpleyaml.exceptions.InvalidConfigurationException;

public class Config {
    private File config;
    public static Config INS;
    private String Database;
    private String Prefix;
    private String Token;
    private String ActivityType;
    private String ActivityString;
    private String Name;
    private Long ownerId;
    private Boolean webhooksStatus;
    private String webhooksURL;

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
        this.ownerId = config.getLong("ownerid");
        this.webhooksStatus = config.getBoolean("webhooks.enabled");
        this.webhooksURL = config.getString("webhooks.url");
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

    // Fetching the bots owner id
    public Long getOwnerId() {
        return ownerId;
    }

    // Fetching the webhooks status
    public Boolean getWebhooksStatus() {
        return webhooksStatus;
    }

    // Fetching the webhooks url
    public String getWebhooksURL() {
        return webhooksURL;
    }
}