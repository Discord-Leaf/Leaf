package uk.toadl3ss.Leaf;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import uk.toadl3ss.Leaf.Listeners.EventListeners;
import uk.toadl3ss.Leaf.Utillites.Config;
import uk.toadl3ss.Leaf.Utillites.Info;
import uk.toadl3ss.Leaf.Utillites.Vanity;

import javax.security.auth.login.LoginException;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static String version = "1.0.0";
    public static DefaultShardManagerBuilder builder;
    public static void main(String[] args) throws LoginException {
        Vanity.printVainity();
        Info.printInfo(version);
        Config.init("application.yml");
        uk.toadl3ss.Leaf.Database.Main.main();
        builder = DefaultShardManagerBuilder.createDefault(
                Config.INS.getToken(),
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_WEBHOOKS
        );
        builder.disableCache(
                CacheFlag.MEMBER_OVERRIDES
        );
        builder.enableCache(
                CacheFlag.VOICE_STATE
        );
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.playing("Leaf - " + version + " Starting."));
        new EventListeners().addEventListeners(builder);
        builder.setShardsTotal(-1);
        builder.build();
    }
}