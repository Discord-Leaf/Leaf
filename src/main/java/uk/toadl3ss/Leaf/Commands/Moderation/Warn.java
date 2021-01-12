package uk.toadl3ss.Leaf.Commands.Moderation;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Database.Main;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.Resources.noPermissions;
import uk.toadl3ss.Leaf.Resources.meNoPermissions;

public class Warn implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            new noPermissions().noPermissionsEmbed(event);
            return;
        }
        if (args.length < 2) {
            event.getChannel().sendMessage("Please provide a member to warn!").queue();
            return;
        }
        if (event.getMessage().getMentionedUsers().isEmpty()) {
            event.getChannel().sendMessage("Please provide a valid member to warn!").queue();
            return;
        }
        final Member target = event.getMessage().getMentionedMembers().get(0);
        final Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            new meNoPermissions().meNoPermissionsEmbed(event);
            return;
        }
        DBObject query = new BasicDBObject("id", target.getId() + event.getGuild().getId());
        DBCursor cursor = Main.user.find(query);
        if (cursor.one() == null) {
            try {
                DBObject user = new BasicDBObject("id", target.getId() + event.getGuild().getId())
                        .append("warns", 0);
                Main.user.insert(user);
            } catch (Error e) {
                event.getChannel().sendMessage("An unknown error occurred. Please try again!").queue();
            }
        }
        BasicDBObject incValue = new BasicDBObject("warns", 1);
        BasicDBObject intModifier = new BasicDBObject("$inc", incValue);
        Main.user.update(query, intModifier, false, false, WriteConcern.SAFE);
        int warnInt = (int) cursor.one().get("warns");
        if (warnInt >= 3) {
            incValue = new BasicDBObject("warns", -3);
            intModifier = new BasicDBObject("$inc", incValue);
            Main.user.update(query, intModifier, false, false, WriteConcern.SAFE);
            event.getGuild()
                    .ban(target, 1)
                    .queue()
            ;
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(0x62bd00);
            embed.setDescription(":white_check_mark: **Banned**" + " " + target.getEffectiveName() + " " + "because the have reached 3 infractions.");
            event.getChannel().sendMessage(embed.build()).queue();
            return;
        }
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0x62bd00);
        embed.setDescription(":white_check_mark: **Warned**" + " " + target.getEffectiveName() + ". " + "They now have" + " " + cursor.one().get("warns") + " " + "warn(s).");
        event.getChannel().sendMessage(embed.build()).queue();
    }
}