package com.rukko.parkour.model.user.match;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

/**
 * @author ViiictorXD
 */

@Getter
@AllArgsConstructor
public class Match {

    private UUID uniqueId;
    private String playerName;

    private String parkourName;

    private long time;
    private long date;

    private MatchResult result;
}
