package uk.toadl3ss.Leaf.Webhooks;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import uk.toadl3ss.Leaf.Utillites.Logger;

public class startHook {
    public static void sendStartHook(ReadyEvent event) {
        if (!Webhooks.isEnabled) {
            return;
        }
        String botName = event.getJDA().getSelfUser().getName();
        String botDiscriminator = event.getJDA().getSelfUser().getDiscriminator();
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(0x62bd00)
                .setDescription(":green_circle:" + " " + botName + "#" + botDiscriminator + " " + "-" + " " + "is now online.")
                .build();
        Webhooks.webhookClient.send(embed);
        Logger.info("Start webhook sent.");
    }
}