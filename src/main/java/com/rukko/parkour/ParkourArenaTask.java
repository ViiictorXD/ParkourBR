package com.rukko.parkour;

import com.rukko.parkour.board.FastBoard;
import com.rukko.parkour.manager.ArenaManagement;
import com.rukko.parkour.manager.BoardManagement;
import com.rukko.parkour.manager.ReplaceManagement;
import com.rukko.parkour.model.Board;
import com.rukko.parkour.model.arena.Arena;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class ParkourArenaTask implements Runnable {

    private final ParkourPlugin plugin;

    @Override
    public void run() {
        final ArenaManagement arenaManagement = plugin.getArenaManagement();
        final BoardManagement boardManagement = plugin.getBoardManagement();
        final ReplaceManagement replaceManagement = plugin.getReplaceManagement();

        final ParkourBoard parkourBoard = plugin.getParkourBoard();

        for (Player player : Bukkit.getOnlinePlayers()) {
            final Arena arena = arenaManagement.match(player.getUniqueId());
            final Board board = boardManagement.match(player);

            if (arena != null)
                arena.setTime(arena.getTime() + TimeUnit.SECONDS.toMillis(1));

            final FastBoard fastBoard = board.getBoard();
            fastBoard.updateLines(replaceManagement.getReplacedLines(player, arena == null
                    ? parkourBoard.getCasualLines()
                    : parkourBoard.getParkourLines()
            ));
        }
    }
}
