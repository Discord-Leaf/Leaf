package uk.toadl3ss.Leaf.Commands.Owner;

import groovy.lang.GroovyShell;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Utillites.Config;

public class Eval implements ICommand {
    private GroovyShell engine;
    private final String imports;
    public Eval() {
        this.engine = new GroovyShell();
        this.imports = "import java.io.*\n" +
                "import java.lang.*\n" +
                "import java.util.*\n" +
                "import java.util.concurrent.*\n" +
                "import net.dv8tion.jda.core.*\n" +
                "import net.dv8tion.jda.core.entities.*\n" +
                "import net.dv8tion.jda.core.entities.impl.*\n" +
                "import net.dv8tion.jda.core.managers.*\n" +
                "import net.dv8tion.jda.core.managers.impl.*\n" +
                "import net.dv8tion.jda.core.utils.*\n";
    }
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) throws InterruptedException {
        String messageArgs = event.getMessage().getContentRaw().replaceFirst("^" + prefix + "eval" + " ", "");
        if (event.getAuthor().getIdLong() == Config.INS.getOwnerId()) {
            try {
                engine.setProperty("args", messageArgs);
                engine.setProperty("event", event);
                engine.setProperty("message", event.getMessage());
                engine.setProperty("channel", event.getChannel());
                engine.setProperty("jda", event.getJDA());
                engine.setProperty("guild", event.getGuild());
                engine.setProperty("member", event.getMember());

                String script = imports + event.getMessage().getContentRaw().split("\\s+", 2)[1];
                Object out = engine.evaluate(script);

                event.getChannel().sendMessage(out == null ? "Executed without error" : out.toString()).queue();
            }
            catch (Exception e) {
                event.getChannel().sendMessage(e.getMessage()).queue();
            }
        }
    }
}