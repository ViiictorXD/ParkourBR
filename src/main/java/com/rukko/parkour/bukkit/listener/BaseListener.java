package com.rukko.parkour.bukkit.listener;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.backend.board.FastBoard;
import com.rukko.parkour.backend.builder.ItemBuilder;
import com.rukko.parkour.bukkit.event.arena.ArenaEndEvent;
import com.rukko.parkour.bukkit.event.arena.ArenaStartEvent;
import com.rukko.parkour.manager.BoardManagement;
import com.rukko.parkour.manager.UserManagement;
import com.rukko.parkour.model.Board;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.model.user.Content;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.model.user.match.Match;
import com.rukko.parkour.repository.user.MatchRepository;
import com.rukko.parkour.backend.serialization.Serializations;
import com.rukko.parkour.settings.Settings;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@RequiredArgsConstructor
public class BaseListener implements Listener {

    private final UserManagement userManagement;
    private final MatchRepository matchRepository;

    private final BoardManagement boardManagement;

    @EventHandler
    private void onArenaStart(ArenaStartEvent event) {
        final Arena arena = event.getArena();

        final User user = userManagement.match(arena.getUniqueId());
        final Content content = user.getContent();

        final Player player = Bukkit.getPlayer(arena.getUniqueId());
        final PlayerInventory inventory = player.getInventory();

        final Board board = boardManagement.match(player);

        final FastBoard newBoard = new FastBoard(player);
        newBoard.updateTitle(Settings.SCOREBOARD_TITLE.asString());

        board.setBoard(newBoard);

        content.setInventoryContent(Serializations.ITEM_STACK_VET_SERIALIZATION.serialize(inventory.getContents()));
        content.setArmorContent(Serializations.ITEM_STACK_VET_SERIALIZATION.serialize(inventory.getArmorContents()));

        inventory.setContents(new ItemStack[0]);
        inventory.setArmorContents(new ItemStack[0]);

        player.updateInventory();

        inventory.setItem(2, new ItemBuilder(Material.BED).name("§cBack last Checkpoint").build());
        inventory.setItem(6, new ItemBuilder(Material.BARRIER).name("§cLeave of Parkour Arena").build());
    }

    @EventHandler
    private void onArenaEnd(ArenaEndEvent event) {
        final Arena arena = event.getArena();
        final Player player = Bukkit.getPlayer(arena.getUniqueId());

        final Match match = new Match(
                UUID.randomUUID(),
                player.getName(),
                arena.getParkour().getRealName(),
                arena.getTime(),
                arena.getDate(),
                arena.getResult()
        );

        final User user = userManagement.match(player.getUniqueId());
        user.getMatches().add(match);

        final Content content = user.getContent();
        final PlayerInventory inventory = player.getInventory();

        inventory.setContents(Serializations.ITEM_STACK_VET_SERIALIZATION.deserialize(content.getInventoryContent()));
        inventory.setArmorContents(Serializations.ITEM_STACK_VET_SERIALIZATION.deserialize(content.getArmorContent()));

        player.updateInventory();

        Bukkit.getScheduler().runTaskLater(ParkourPlugin.getPlugin(), () -> {
            if (!player.isOnline())
                return;

            final Board board = boardManagement.match(player);

            if (board == null)
                return;

            final FastBoard newBoard = new FastBoard(player);
            newBoard.updateTitle(Settings.SCOREBOARD_TITLE.asString());

            board.setBoard(newBoard);
        }, 20L);

        matchRepository.constructor(arena.getUniqueId(), match);
    }
}
