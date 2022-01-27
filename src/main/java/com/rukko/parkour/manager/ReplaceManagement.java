package com.rukko.parkour.manager;

import com.rukko.parkour.backend.replace.Replace;
import com.rukko.parkour.backend.replace.impl.ArenaReplace;
import com.rukko.parkour.backend.replace.impl.CasualReplace;
import com.rukko.parkour.backend.replace.impl.MatchReplace;
import com.rukko.parkour.backend.replace.impl.RankingReplace;
import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.model.user.match.Match;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
public class ReplaceManagement {

    private final Map<Class<?>, Replace<?>> replaces = new HashMap<>();

    public void constructor(Class<?> clazz, Replace<?> replace) {
        replaces.put(clazz, replace);
    }

    public String getReplacedLine(Player player, String line, Object... values) {
        for (Object value : values) {
            if (value == null)
                continue;

            final Replace replace = replaces.get(value.getClass());
            line = replace.replace(player, value, line);
        }
        return line;
    }

    public List<String> getReplacedLines(Player player, List<String> lines, Object... values) {
        final List<String> clone = new ArrayList<>(lines);
        for (int index = 0; index < lines.size(); index++)
            clone.set(index, getReplacedLine(player, clone.get(index), values));

        return clone;
    }

    public void registerDefaults() {
        constructor(Arena.class, new ArenaReplace());
        constructor(User.class, new CasualReplace());
        constructor(Match.class, new MatchReplace());
        constructor(Ranking.class, new RankingReplace());
    }
}
