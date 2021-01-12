package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Uptime implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeMXBean.getUptime();
        long uptimeInSeconds = uptime / 1000;
        long numberOfHours = uptimeInSeconds / (60 * 60);
        long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = uptimeInSeconds % 60;

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Uptime");
        embed.setColor(0x62bd00);
        embed.addField("Hours:", String.valueOf(numberOfHours), true);
        embed.addField("Minutes:", String.valueOf(numberOfMinutes), true);
        embed.addField("Seconds:", String.valueOf(numberOfSeconds), true);
        event.getChannel().sendMessage(embed.build()).queue();
    }
}