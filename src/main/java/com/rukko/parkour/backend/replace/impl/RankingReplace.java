package com.rukko.parkour.backend.replace.impl;

import com.rukko.parkour.backend.format.FormatTime;
import com.rukko.parkour.backend.replace.Replace;
import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.user.match.Match;
import org.bukkit.entity.Player;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class RankingReplace implements Replace<Ranking> {

    @Override
    public String replace(Player player, Ranking subject, String text) {
        return text.replace("{ranking_position}", String.valueOf(subject.getPosition()))
                .replace("{ranking_player_name}", subject.getPlayerName())
                .replace("{ranking_arena_name}", subject.getArenaName())
                .replace("{ranking_arena_result}", subject.getResult().beautifulName())
                .replace("{ranking_arena_elapse_time}", FormatTime.buildString(subject.getElapsed()))
                .replace("{ranking_arena_date}", FormatTime.format(subject.getDate()));
    }
}
