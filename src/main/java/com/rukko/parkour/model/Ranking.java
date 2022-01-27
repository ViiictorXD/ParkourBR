package com.rukko.parkour.model;

import com.rukko.parkour.model.user.match.MatchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

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
