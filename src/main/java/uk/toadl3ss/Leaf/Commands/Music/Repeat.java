package uk.toadl3ss.Leaf.Commands.Music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.toadl3ss.Leaf.Interfaces.ICommand;
import uk.toadl3ss.Leaf.LavaPlayer.GuildMusicManager;
import uk.toadl3ss.Leaf.LavaPlayer.PlayerManager;

public class Repeat implements ICommand {
    @Override
    public void Action(MessageReceivedEvent event, String[] args, String prefix) {
        final TextChannel channel = (TextChannel) event.getChannel();
        final Member self = event.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work.").queue();
            return;
        }

        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("I need to be in a voice channel for this to work.").queue();
            return;
        }
        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work!").queue();
            return;
        }
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final boolean newRepeating = !musicManager.scheduler.repeating;
        musicManager.scheduler.repeating = newRepeating;
        channel.sendMessageFormat("The player has been set to **%s**.", newRepeating ? "repeating" : "not repeating").queue();
    }
}