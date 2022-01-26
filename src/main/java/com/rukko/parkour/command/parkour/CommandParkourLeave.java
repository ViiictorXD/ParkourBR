package com.rukko.parkour.command.parkour;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.event.arena.ArenaEndEvent;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.model.arena.Arena;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class CommandParkourLeave {

    private final ParkourPlugin plugin;

    @Command(
            name = "parkour.leave",

            permission = "parkour.commands.leave",

            target = CommandTarget.PLAYER
    )
    public void onParkourLeave(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        final Arena arena = plugin.getArenaManagement().match(sender.getUniqueId());
        if (arena == null) {
            sender.sendMessage("§cYou are not in an parkour arena.");
            return;
        }

        plugin.getArenaManagement().destructor(arena);

        arena.stop();

        sender.teleport(arena.getParkour().getExit());
        sender.sendMessage("§cYou left the parkour arena.");
    }
}
