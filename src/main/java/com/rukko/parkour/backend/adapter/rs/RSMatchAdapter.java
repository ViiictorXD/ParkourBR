package com.rukko.parkour.backend.adapter.rs;

import com.rukko.parkour.model.user.match.Match;
import com.rukko.parkour.model.user.match.MatchResult;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class RSMatchAdapter implements RSAdapter<List<Match>> {

    @SneakyThrows
    @Override
    public List<Match> adapt(ResultSet resultSet) {
        final List<Match> matches = new ArrayList<>();

        while(resultSet.next()) {
            final UUID matchId = UUID.fromString(resultSet.getString("matchId"));
            final String playerName = resultSet.getString("playerName");

            final String parkourName = resultSet.getString("parkourName");

            final long time = resultSet.getLong("time");
            final long date = resultSet.getLong("date");

            final MatchResult result = MatchResult.valueOf(resultSet.getString("result"));

            matches.add(new Match(matchId, playerName, parkourName, time, date, result));
        }
        return matches;
    }
}
