package com.rukko.parkour.manager;

import com.rukko.parkour.model.Ranking;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
@Setter
public class RankingManagement {

    private List<Ranking> rankings = new ArrayList<>();
    private long next;

    public Ranking match(int position) {
        return rankings.stream().filter(ranking -> ranking.getPosition() == position).findAny().orElse(null);
    }

    public void update(List<Ranking> rankings) {
        if (System.currentTimeMillis() < getNext())
            return;

        setRankings(rankings);
        setNext(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
    }
}
