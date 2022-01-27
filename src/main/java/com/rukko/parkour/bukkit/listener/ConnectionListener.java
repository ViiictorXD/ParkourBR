package com.rukko.parkour.bukkit.listener;

import com.rukko.parkour.ParkourPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

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
