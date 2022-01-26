package com.rukko.parkour.model;

import com.rukko.parkour.model.user.match.MatchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ViiictorXD
 */

@Getter
@AllArgsConstructor
public class Ranking {

    private String playerName;
    private String arenaName;

    private int position;

    private long elapsed;
    private long date;

    private MatchResult result;
}
