package com.rukko.parkour.bukkit.event.arena;

import com.rukko.parkour.bukkit.event.Event;
import com.rukko.parkour.model.arena.Arena;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
@RequiredArgsConstructor
public class ArenaStartEvent extends Event {

    private final Arena arena;
}
