package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Main;

public class Info implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Leaf - Info");
        builder.addField("Library", "JDA", true);
        builder.addField("Author", "Toadless#0001", true);
        builder.addField("Library", "JDA", true);
        builder.addField("Version", Main.version, true);
        builder.addField("Servers", String.valueOf(event.getJDA().getGuilds().size()), true);
        builder.addField("Website", "https://leaf-bot.xyz", true);
        builder.setColor(0x62bd00);
        event.getChannel().sendMessage(builder.build()).queue();
    }
}