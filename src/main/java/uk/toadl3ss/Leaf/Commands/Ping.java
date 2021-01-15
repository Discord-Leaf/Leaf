package uk.toadl3ss.Leaf.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;

public class Ping implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) throws InterruptedException {
        event.getJDA().getRestPing().queue(aLong -> {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Leaf - Ping");
            embed.addField("Websocket:", String.valueOf(event.getJDA().getGatewayPing()),false);
            embed.addField("Rest:", String.valueOf(aLong), false);
            embed.setColor(0x62bd00);
            event.getChannel().sendMessage(embed.build()).queue();
        });
    }
}