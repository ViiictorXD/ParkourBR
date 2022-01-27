package com.rukko.parkour.backend.replace.impl;

import com.rukko.parkour.backend.format.FormatTime;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.backend.replace.Replace;
import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class ArenaReplace implements Replace<Arena> {

    @Override
    public String replace(Player player, Arena subject, String text) {
        final String checkpointPattern = subject != null
                ? subject.getCheckpoint() != null
                    ? subject.getCheckpoint().getIndex() + "/" + subject.getParkour().getCheckpoints().size()
                    : "None"
                : "None";

        final String elapsedTimePattern = subject != null
                ? FormatTime.buildString(subject.getTime())
                : "None";

        return text.replace("{checkpoint_pattern}", checkpointPattern)
                .replace("{elapsed_pattern}", elapsedTimePattern);
    }
}
