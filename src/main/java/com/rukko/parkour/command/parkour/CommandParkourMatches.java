package com.rukko.parkour.command.parkour;

import com.google.common.collect.ImmutableMap;
import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.view.MatchView;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class CommandParkourMatches {

    private final ParkourPlugin plugin;

    @Command(
            name = "parkour.matches",

            permission = "parkour.commands.matches",

            target = CommandTarget.PLAYER
    )
    public void onParkour(Context<Player> context) {
        final Player sender = context.getSender();

        final User user = plugin.getUserManagement().match(sender.getUniqueId());
        final ViewFrame viewFrame = plugin.getViewFrame();

        viewFrame.open(MatchView.class, sender, ImmutableMap.of(
                "matches", user.getMatches()
        ));
    }
}
