package uk.toadl3ss.Leaf.Events;

import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import uk.toadl3ss.Leaf.Utillites.Logger;
import uk.toadl3ss.Leaf.Webhooks.stopHook;

public class Shutdown extends ListenerAdapter {
    @Override
    public void onShutdown(ShutdownEvent event) {
        Logger.info("Leaf is now offline");
        stopHook.stopWebHook(event);
    }
}