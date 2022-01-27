package com.rukko.parkour.manager;

import com.rukko.parkour.model.Board;
import com.rukko.parkour.model.user.User;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
public class BoardManagement {

    private final Set<Board> boards = new HashSet<>();

    public Board match(Player player) {
        return boards.stream().filter(board -> board.getPlayer().getUniqueId().equals(player.getUniqueId()))
                .findAny().orElse(null);
    }

    public void constructor(Board board) {
        boards.add(board);
    }

    public void destructor(Player player) {
        final Board match = match(player);

        if (match != null)
            boards.remove(match);
    }

    public boolean exists(Player player) {
        return match(player) != null;
    }
}
