package com.rukko.parkour.model.user.match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
@RequiredArgsConstructor
public enum MatchResult {

    COMPLETED("Completed", ChatColor.GREEN),
    DESIST("Uncompleted", ChatColor.RED);

    private final String name;
    private final ChatColor color;

    public String beautifulName() {
        return color + name;
    }
}
