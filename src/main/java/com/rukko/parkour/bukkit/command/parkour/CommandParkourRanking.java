package com.rukko.parkour.bukkit.command.parkour;

import com.google.common.collect.ImmutableMap;
import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.bukkit.view.RankingView;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@RequiredArgsConstructor
public class CommandParkourRanking {

    private final ParkourPlugin plugin;

    @Command(
            name = "parkour.ranking",

            permission = "parkour.commands.ranking",

            target = CommandTarget.PLAYER
    )
    public void onParkour(Context<Player> context) {
        final Player sender = context.getSender();
        final ViewFrame viewFrame = plugin.getViewFrame();

        viewFrame.open(RankingView.class, sender, ImmutableMap.of(
                "rankings", plugin.getRankingManagement().getRankings()
        ));
    }
}
