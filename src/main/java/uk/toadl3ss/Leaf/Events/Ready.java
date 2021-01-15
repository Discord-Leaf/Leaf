package uk.toadl3ss.Leaf.Events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.ShardManager;
import uk.toadl3ss.Leaf.Main;
import uk.toadl3ss.Leaf.Utillites.Logger;
import uk.toadl3ss.Leaf.Utillites.setActivity;
import uk.toadl3ss.Leaf.Webhooks.startHook;

public class Ready extends ListenerAdapter {
    private int shards = 0;
    @Override
    public void onReady(ReadyEvent event) {
        JDA jda = event.getJDA();
        ShardManager shardManager = jda.getShardManager();
        shards++;
        Logger.info(
                "Shard" +
                        " " +
                        jda.getShardInfo().getShardId() +
                        " " +
                        "(" +
                        jda.getGuildCache().size() +
                        " " +
                        "Guilds)" +
                        " " +
                        "ready!"
        );
        if(shards == jda.getShardInfo().getShardTotal()){
            new setActivity().setActivity(jda);
            startHook.sendStartHook(event);
            Logger.info( // Loaded Bot {} vBOT_VERSION with {} shard(s) and {} guilds!
                    "Loaded Bot" +
                            " " +
                            jda.getSelfUser().getAsTag() +
                            " " +
                            "v" +
                            Main.version +
                            " " +
                            "with" +
                            " " +
                            shardManager.getShardCache().size() +
                            " " +
                            "shards(s) and" +
                            " " +
                            shardManager.getGuildCache().size() +
                            " " +
                            "guilds!"
            );
        }
    }
}