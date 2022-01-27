package com.rukko.parkour.backend.replace.impl;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.backend.format.FormatTime;
import com.rukko.parkour.manager.RankingManagement;
import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.backend.replace.Replace;
import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class CasualReplace implements Replace<User> {

    private static final ParkourPlugin PLUGIN = ParkourPlugin.getPlugin();

    @Override
    public String replace(Player player, User subject, String text) {
        final String bestAttemptPattern = subject.getBestMatch() != null
                ? FormatTime.buildString(subject.getBestMatch().getTime())
                : "None";

        final RankingManagement rankingManagement = PLUGIN.getRankingManagement();
        for (int index = 0; index < 5; index++) {
            final Ranking ranking = rankingManagement.match(index + 1);

            if (ranking == null) {
                text = text.replace("{ranking_name_" + (index + 1) + "}", "None")
                        .replace("{ranking_elapsed_" + (index + 1) + "}", "0");
                continue;
            }

            text = text.replace("{ranking_name_" + (index + 1) + "}", ranking.getPlayerName())
                    .replace("{ranking_elapsed_" + (index + 1) + "}", FormatTime.buildString(ranking.getElapsed()));
        }

        return text.replace("{attempt_pattern}", bestAttemptPattern);
    }
}
