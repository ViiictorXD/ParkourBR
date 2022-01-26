package com.rukko.parkour.replace.impl;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.format.FormatTime;
import com.rukko.parkour.manager.ArenaManagement;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.replace.Replace;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */
public class ArenaReplace implements Replace {

    private static final ParkourPlugin PLUGIN = ParkourPlugin.getPlugin();

    @Override
    public String replace(Player player, String text) {
        final Arena arena = PLUGIN.getArenaManagement().match(player.getUniqueId());

        final String checkpointPattern = arena != null
                ? arena.getCheckpoint() != null
                    ? arena.getCheckpoint().getIndex() + "/" + arena.getParkour().getCheckpoints().size()
                    : "None"
                : "None";

        final String elapsedTimePattern = arena != null
                ? FormatTime.buildString(arena.getTime())
                : "None";

        return text.replace("{checkpoint_pattern}", checkpointPattern)
                .replace("{elapsed_pattern}", elapsedTimePattern);
    }
}
