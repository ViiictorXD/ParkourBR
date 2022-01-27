package com.rukko.parkour.bukkit.command.arena;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.backend.Utils;
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
public class CommandArenaRemove {

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.remove",
            aliases = {"remover"},

            permission = "arena.commands.remove",

            target = CommandTarget.PLAYER
    )
    public void onArenaRemove(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage(Messages.MESSAGE_ARENA_REMOVE_INFO.get());
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage(Messages.MESSAGE_ARENA_NOT_EXISTS.get());
            return;
        }

        final Integer index = Utils.parseInt(args[1]);
        if (index == null || index < 0) {
            sender.sendMessage(Messages.MESSAGE_VALID_INDEX.get());
            return;
        }

        final Checkpoint checkpoint = parkour.match(index);
        if (checkpoint == null) {
            sender.sendMessage(Messages.MESSAGE_CHECKPOINT_NOT_EXISTS.get());
            return;
        }

        parkour.getCheckpoints().remove(checkpoint);
        plugin.getParkourLoadable().constructor(parkour);

        sender.sendMessage(Messages.MESSAGE_ARENA_REMOVE_SUCCESS.get(arenaName));
    }
}
