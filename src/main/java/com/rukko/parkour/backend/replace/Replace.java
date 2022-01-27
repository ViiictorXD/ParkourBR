package com.rukko.parkour.backend.replace;

import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface Replace<S> {

    String replace(Player player, S subject, String text);
}
