package com.rukko.parkour.repository.user;

import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.user.match.Match;
import com.rukko.parkour.repository.Repository;

import java.util.List;
import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface MatchRepository extends Repository {

    List<Match> match(UUID uniqueId);

    void constructor(UUID uniqueId, Match match);

    List<Ranking> getRanking();
}
