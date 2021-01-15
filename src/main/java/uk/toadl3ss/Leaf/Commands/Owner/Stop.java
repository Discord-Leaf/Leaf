package uk.toadl3ss.Leaf.Commands.Owner;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Utillites.Config;

public class Stop implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) throws InterruptedException {
        if (event.getAuthor().getIdLong() == Config.INS.getOwnerId()) {
            event.getChannel().sendMessage("Bot successfully exiting.").queue();
            System.exit(0);
        }
    }
}