package com.rukko.parkour.model.user;

import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.model.user.match.Match;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * @author ViiictorXD
 */

@Getter
@RequiredArgsConstructor
public class User {

    private final UUID uniqueId;
    private final String realName;

    private final Content content = new Content();

    @Setter
    private Arena arena;

    @Setter
    private List<Match> matches;

    public Match getBestMatch() {
        return matches.stream().sorted(Comparator.comparingLong(Match::getTime)).findAny().orElse(null);
    }
}
