package com.rukko.parkour;

import com.rukko.parkour.backend.board.FastBoard;
import com.rukko.parkour.manager.ArenaManagement;
import com.rukko.parkour.manager.BoardManagement;
import com.rukko.parkour.manager.ReplaceManagement;
import com.rukko.parkour.manager.UserManagement;
import com.rukko.parkour.model.Board;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.settings.Settings;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@RequiredArgsConstructor
public class ParkourArenaTask implements Runnable {

    private final ParkourPlugin plugin;

    @Override
    public void run() {
        final ArenaManagement arenaManagement = plugin.getArenaManagement();
        final BoardManagement boardManagement = plugin.getBoardManagement();
        final ReplaceManagement replaceManagement = plugin.getReplaceManagement();
        final UserManagement userManagement = plugin.getUserManagement();

        for (Player player : Bukkit.getOnlinePlayers()) {
            final Arena arena = arenaManagement.match(player.getUniqueId());
            final Board board = boardManagement.match(player);
            final User user = userManagement.match(player.getUniqueId());

            if (arena != null)
                arena.setTime(arena.getTime() + TimeUnit.SECONDS.toMillis(1));

            final FastBoard fastBoard = board.getBoard();
            fastBoard.updateLines(replaceManagement.getReplacedLines(player, arena == null
                    ? Settings.SCOREBOARD_CASUAL_LINES.asListString()
                    : Settings.SCOREBOARD_PARKOUR_LINES.asListString()
            , arena, user));
        }
    }
}
