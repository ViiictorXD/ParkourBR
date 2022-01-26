package com.rukko.parkour.listener;

import com.rukko.parkour.ParkourPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class ConnectionListener implements Listener {

    private final ParkourPlugin plugin;

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        plugin.constructor(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        plugin.getBoardManagement().destructor(player);
    }
}
