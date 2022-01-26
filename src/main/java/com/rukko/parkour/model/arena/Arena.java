package com.rukko.parkour.model.arena;

import com.rukko.parkour.board.FastBoard;
import com.rukko.parkour.format.FormatTime;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.model.user.match.MatchResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ViiictorXD
 */
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
