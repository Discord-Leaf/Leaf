package uk.toadl3ss.Leaf.Events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import uk.toadl3ss.Leaf.Utillites.Logger;
import uk.toadl3ss.Leaf.Utillites.createGuild;;

public class guildJoinEvent extends ListenerAdapter {
    public void onGuildJoin(GuildJoinEvent event) {
        Logger.info("Guild added. Creating database.");
        final Guild guild = event.getGuild();
        final String id = guild.getId();
        final String name = guild.getName();
        final String iconId = guild.getIconId();
        new createGuild().createGuild(id, name, iconId);
    }
}