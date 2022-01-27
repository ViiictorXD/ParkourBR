package com.rukko.parkour.model;

import com.rukko.parkour.backend.board.FastBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
@AllArgsConstructor
public class Board {

    private Player player;

    @Setter
    private FastBoard board;
}
