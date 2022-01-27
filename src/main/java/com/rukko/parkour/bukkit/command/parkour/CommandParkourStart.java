package com.rukko.parkour.bukkit.command.parkour;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.backend.board.FastBoard;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.settings.Messages;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@RequiredArgsConstructor
public class CommandParkourStart {

    private final ParkourPlugin plugin;

    @Command(
            name = "parkour.start",
            aliases = {"iniciar"},

            permission = "parkour.commands.start",

            target = CommandTarget.PLAYER
    )
    public void onParkourStart(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage(Messages.MESSAGE_PARKOUR_START_INFO.get());
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage(Messages.MESSAGE_ARENA_NOT_EXISTS.get());
            return;
        }

        if (!parkour.available()) {
            sender.sendMessage(Messages.MESSAGE_PARKOUR_NOT_AVAILABLE.get());
            return;
        }

        if (plugin.getArenaManagement().exists(sender.getUniqueId())) {
            sender.sendMessage(Messages.MESSAGE_PARKOUR_ALREADY_PLAYING.get());
            return;
        }

        plugin.getArenaManagement().constructor(new Arena(sender.getUniqueId(), parkour, new FastBoard(sender)));

        sender.teleport(parkour.getEntry());
        sender.sendMessage(Messages.MESSAGE_PARKOUR_START_SUCCESS.get());
    }
}
