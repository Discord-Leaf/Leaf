package uk.toadl3ss.Leaf.Webhooks;

import club.minnced.discord.webhook.WebhookClient;
import uk.toadl3ss.Leaf.Utillites.Config;

public class Webhooks {
    public static boolean isEnabled = true;
    public static WebhookClient webhookClient;
    public Webhooks() {
        if (!Config.INS.getWebhooksStatus()) {
            isEnabled = false;
        }
        if (!isEnabled) {
            return;
        }
        webhookClient = WebhookClient.withUrl(Config.INS.getWebhooksURL());
    }
}