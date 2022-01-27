package com.rukko.parkour.model.user.match;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

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
