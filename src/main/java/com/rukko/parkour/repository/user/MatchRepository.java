package com.rukko.parkour.repository.user;

import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.user.match.Match;
import com.rukko.parkour.repository.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author ViiictorXD
 */
public interface MatchRepository extends Repository {

    List<Match> match(UUID uniqueId);

    void constructor(UUID uniqueId, Match match);

    List<Ranking> getRanking();
}
