package com.rukko.parkour.repository.user;

import com.rukko.parkour.backend.adapter.rs.RSMatchAdapter;
import com.rukko.parkour.backend.connection.ConnectionFactory;
import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.user.match.Match;
import com.rukko.parkour.model.user.match.MatchResult;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepository {

    private final ConnectionFactory connectionFactory;
    private final RSMatchAdapter adapter = new RSMatchAdapter();

    @Override
    public List<Match> match(UUID uniqueId) {
        final Connection connection = connectionFactory.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(MatchConstants.MATCH)) {
            preparedStatement.setString(1, uniqueId.toString());

            return adapter.adapt(preparedStatement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void constructor(UUID uniqueId, Match match) {
        final Connection connection = connectionFactory.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(MatchConstants.CONSTRUCTOR)) {
            preparedStatement.setString(1, uniqueId.toString());
            preparedStatement.setString(2, match.getPlayerName());

            preparedStatement.setString(3, match.getUniqueId().toString());
            preparedStatement.setString(4, match.getParkourName());

            preparedStatement.setLong(5, match.getTime());
            preparedStatement.setLong(6, match.getTime());

            preparedStatement.setString(7, match.getResult().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Ranking> getRanking() {
        final List<Ranking> rankings = new ArrayList<>();

        final Connection connection = connectionFactory.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(MatchConstants.RANKING)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                int position = 0;

                while (resultSet.next()) {
                    final String playerName = resultSet.getString("playerName");
                    final String arenaName = resultSet.getString("parkourName");

                    final long elapsed = resultSet.getLong("time");
                    final long date = resultSet.getLong("date");

                    final MatchResult result = MatchResult.valueOf(resultSet.getString("result"));

                    rankings.add(new Ranking(playerName, arenaName, ++position, elapsed, date, result));
                }

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rankings;
    }

    @Override
    public void createNonExistentTable() {
        final Connection connection = connectionFactory.getConnection();
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(MatchConstants.CREATE_TABLE);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
