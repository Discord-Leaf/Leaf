package uk.toadl3ss.Leaf.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Commands.*;
import uk.toadl3ss.Leaf.Commands.Moderation.Ban;
import uk.toadl3ss.Leaf.Commands.Moderation.Kick;
import uk.toadl3ss.Leaf.Commands.Moderation.Warn;
import uk.toadl3ss.Leaf.Commands.Music.*;
import uk.toadl3ss.Leaf.Commands.Owner.Eval;
import uk.toadl3ss.Leaf.Commands.RoleReactions.Add;
import uk.toadl3ss.Leaf.Commands.RoleReactions.Remove;
import uk.toadl3ss.Leaf.Database.getMusic;

public class Commands {
    public static void Commands(MessageReceivedEvent event, String command, String[] args, String prefix) throws InterruptedException {
        final String id = event.getGuild().getId();
        if (getMusic.getMusic(id)) {
            switch (command.toLowerCase()) {
                // Music commands
                case "play":
                    new Play().Action(event, args, prefix);
                    break;
                case "queue":
                    new Queue().Action(event, args, prefix);
                    break;
                case "skip":
                    new Skip().Action(event, args, prefix);
                    break;
                case "stop":
                    new Stop().Action(event, args, prefix);
                    return;
                case "summon":
                case "join":
                    new Join().Action(event, args, prefix);
                    break;
                case "disconnect":
                case "leave":
                    new Leave().Action(event, args, prefix);
                    return;
                case "nowplaying":
                    new NowPlaying().Action(event, args, prefix);
                    break;
                case "loop":
                case "repeat":
                    new Repeat().Action(event, args, prefix);
                    break;

                default:
                    break;
            }
        }

        switch (command.toLowerCase()) {
            case "info":
                new Info().Action(event, args, prefix);
                break;
            case "ban":
                new Ban().Action(event, args, prefix);
                break;
            case "kick":
                new Kick().Action(event, args, prefix);
                break;
            case "avatar":
                new Avatar().Action(event, args, prefix);
                break;
            case "invite":
                new Invite().Action(event, args, prefix);
                break;
            case "uptime":
                new Uptime().Action(event, args, prefix);
                break;
            case "warn":
                new Warn().Action(event, args, prefix);
                break;
            case "clear":
                new Clear().Action(event, args, prefix);
                break;
            case "prefix":
                new Prefix().Action(event, args, prefix);
                break;
            case "ping":
                new Ping().Action(event, args, prefix);
                break;

                //Owner
            case "eval":
                new Eval().Action(event, args, prefix);
                break;
            case "shutdown":
            case "stop":
                new uk.toadl3ss.Leaf.Commands.Owner.Stop().Action(event, args, prefix);
                break;

            //Role reactions
            case "rradd":
                new Add().Action(event, args, prefix);
                break;
            case "rrremove":
                new Remove().Action(event, args, prefix);
                break;

            //Tickets
            case "setuptickets":
                new setupTickets().Action(event, args, prefix);
                break;


            default:
                new CustomCommands().Action(event, args, prefix);
                break;
        }
    }
}