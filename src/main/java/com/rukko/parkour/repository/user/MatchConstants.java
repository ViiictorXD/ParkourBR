package com.rukko.parkour.repository.user;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface MatchConstants {

    String MATCH = "SELECT * FROM parkour_matches WHERE uniqueId = ?";

    String CONSTRUCTOR = "INSERT INTO parkour_matches (uniqueId, playerName, matchId, parkourName, time, date, result) VALUES (?, ?, ?, ?, ?, ?, ?)";

    String RANKING = "SELECT * FROM parkour_matches ORDER BY time ASC";

    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS parkour_matches (" +
            "uniqueId VARCHAR(36) NOT NULL, " +
            "playerName VARCHAR(16) NOT NULL, " +
            "matchId VARCHAR(36) NOT NULL, " +
            "parkourName VARCHAR(36) NOT NULL, " +
            "time LONG NOT NULL, " +
            "date LONG NOT NULL, " +
            "result VARCHAR(10) NOT NULL" +
            ")";
}
