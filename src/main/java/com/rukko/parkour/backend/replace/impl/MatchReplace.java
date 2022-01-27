package com.rukko.parkour.backend.replace.impl;

import com.rukko.parkour.backend.format.FormatTime;
import com.rukko.parkour.backend.replace.Replace;
import com.rukko.parkour.model.user.match.Match;
import org.bukkit.entity.Player;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class MatchReplace implements Replace<Match> {

    @Override
    public String replace(Player player, Match subject, String text) {
        return text.replace("{match_id}", String.valueOf(subject.getUniqueId()))
                .replace("{match_parkour_name}", subject.getParkourName())
                .replace("{match_result}", subject.getResult().beautifulName())
                .replace("{match_time}", FormatTime.buildString(subject.getTime()))
                .replace("{match_date}", FormatTime.format(subject.getDate()));
    }
}
