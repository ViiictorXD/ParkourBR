package com.rukko.parkour.listener;

import com.rukko.parkour.bar.ActionBar;
import com.rukko.parkour.event.arena.ArenaEndEvent;
import com.rukko.parkour.manager.ArenaManagement;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.model.user.match.MatchResult;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final ArenaManagement arenaManagement;

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getX() != event.getTo().getX()
                || event.getFrom().getY() != event.getTo().getY()
                || event.getFrom().getZ() != event.getTo().getZ()) {
            final Player player = event.getPlayer();

            final Arena arena = arenaManagement.match(player.getUniqueId());
            if (arena == null)
                return;

            final Parkour parkour = arena.getParkour();

            final Checkpoint checkpoint = parkour.match(player.getLocation());
            if (checkpoint != null &&
                    (arena.getCheckpoint() == null || checkpoint.getIndex() > arena.getCheckpoint().getIndex())) {
                arena.setCheckpoint(checkpoint);

                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
            }

            if (parkour.isNearEnd(player.getLocation())) {
                arena.setResult(MatchResult.COMPLETED);

                arenaManagement.destructor(arena);

                player.teleport(arena.getParkour().getExit());
                player.sendMessage("§aYou have won that parkour arena!");
            }
        }
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        final Arena arena = arenaManagement.match(player.getUniqueId());
        if (arena == null)
            return;

        arenaManagement.destructor(arena);

        player.teleport(arena.getParkour().getExit());
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        final ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR)
            return;

        final Player player = event.getPlayer();

        final Arena arena = arenaManagement.match(player.getUniqueId());
        if (arena == null)
            return;

        switch (item.getType()) {
            case BED: {
                if (arena.getCheckpoint() == null)
                    return;

                player.teleport(arena.getCheckpoint().getLocation());
                break;
            }
            case BARRIER: {
                arenaManagement.destructor(arena);

                player.teleport(arena.getParkour().getExit());
                player.sendMessage("§cYou left the parkour arena.");
                break;
            }
        }
    }

    @EventHandler
    private void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(arenaManagement.exists(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    private void onPlayerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(arenaManagement.exists(event.getPlayer().getUniqueId()));
    }
}
