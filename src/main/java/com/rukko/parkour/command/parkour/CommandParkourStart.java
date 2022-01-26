package com.rukko.parkour.command.parkour;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.board.FastBoard;
import com.rukko.parkour.manager.UserManagement;
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
public class CommandParkourStart {

    private final ParkourPlugin plugin;

    @Command(
            name = "parkour.start",

            permission = "parkour.commands.start",

            target = CommandTarget.PLAYER
    )
    public void onParkourStart(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage("§cUse '/parkour start <name>' to start a parkour arena.");
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage("§cA parkour arena with that name doesn't exists.");
            return;
        }

        if (!parkour.available()) {
            sender.sendMessage("§cThat arena parkour isn't available to use.");
            return;
        }

        if (plugin.getArenaManagement().exists(sender.getUniqueId())) {
            sender.sendMessage("§cYou are already in an arena. To exit, use: '/parkour exit'.");
        }

        plugin.getArenaManagement().constructor(new Arena(sender.getUniqueId(), parkour, new FastBoard(sender)));

        sender.teleport(parkour.getEntry());
        sender.sendMessage("§aYou entered the parkour arena!");
    }
}
