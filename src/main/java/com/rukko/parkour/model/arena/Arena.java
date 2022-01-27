package com.rukko.parkour.model.arena;

import com.rukko.parkour.backend.board.FastBoard;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.model.user.match.MatchResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
@Getter
@Setter
@RequiredArgsConstructor
public class Arena {

    private final UUID uniqueId;
    private final Parkour parkour;

    private final FastBoard board;

    private Checkpoint checkpoint;
    private MatchResult result = MatchResult.DESIST;

    private long time = 0L;
    private long date = System.currentTimeMillis();

    public void update() {
    }

    public void stop() {
        board.delete();
    }
}
