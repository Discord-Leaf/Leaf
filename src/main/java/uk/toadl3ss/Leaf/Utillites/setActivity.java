package uk.toadl3ss.Leaf.Utillites;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.ShardManager;

public class setActivity {
    public void setActivity(JDA jda) {
        ShardManager shardManager = jda.getShardManager();
        String ConfigActivityType = Config.INS.getActivityType().toUpperCase();

        //If activity type is PLAYING we will set it to playing
        if (ConfigActivityType.equals("PLAYING")) {
            shardManager.setPresence(OnlineStatus.ONLINE, Activity.of(
                    Activity.ActivityType.DEFAULT,
                    Config.INS.getActivityString()
            ));
            return;
        }

        //If activity type if WATCHING we will set it to watching
        if (ConfigActivityType.equals("WATCHING")) {
            shardManager.setPresence(OnlineStatus.ONLINE, Activity.of(
                    Activity.ActivityType.WATCHING,
                    Config.INS.getActivityString()
            ));
            return;
        }

        //If activity type if LISTENING we will set it to LISTENING
        if (ConfigActivityType.equals("LISTENING")) {
            shardManager.setPresence(OnlineStatus.ONLINE, Activity.of(
                    Activity.ActivityType.LISTENING,
                    Config.INS.getActivityString()
            ));
            return;
        }
        return;
    }
}