package uk.toadl3ss.Leaf.Commands;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;

public class CustomCommands implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        DBObject query = new BasicDBObject("guildId", event.getGuild().getId());
        DBCursor cursor = uk.toadl3ss.Leaf.Database.Main.guild.find(query);
        BasicDBList commands = (BasicDBList) cursor.one().get("Commands");
        String argsNoPrefix = args[0].replaceFirst("^" + prefix, "");
        for (Object command : commands) {
            BasicDBObject parsedCommand = (BasicDBObject) JSON.parse(command.toString());
            if (argsNoPrefix.toLowerCase().startsWith(parsedCommand.get("Usage").toString().toLowerCase())) {
                event.getChannel().sendMessage(parsedCommand.get("Response").toString()).queue();
                return;
            }
            return;
        }
    }
}