package uk.toadl3ss.Leaf.Listeners;

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import uk.toadl3ss.Leaf.Events.*;

public class EventListeners {
    public void addEventListeners(DefaultShardManagerBuilder builder) {
        builder.addEventListeners(
                new Ready(),
                new CommandHandler(),
                new guildJoinEvent(),
                new guildLeaveEvent(),
                new messageReactionAddTicket(),
                new messageReactionAddComplete(),
                new messageReactionAddRR()
        );
    }
}