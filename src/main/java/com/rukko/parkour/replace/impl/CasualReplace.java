package com.rukko.parkour.replace.impl;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.format.FormatTime;
import com.rukko.parkour.manager.RankingManagement;
import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.replace.Replace;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */
public class CasualReplace implements Replace {

    private static final ParkourPlugin PLUGIN = ParkourPlugin.getPlugin();

    @Override
    public String replace(Player player, String text) {
        final User user = PLUGIN.getUserManagement().match(player.getUniqueId());

        final String bestAttemptPattern = user.getBestMatch() != null
                ? FormatTime.buildString(user.getBestMatch().getTime())
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
