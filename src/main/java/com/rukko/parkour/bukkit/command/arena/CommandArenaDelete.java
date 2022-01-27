package com.rukko.parkour.bukkit.command.arena;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.Parkour;
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
public class CommandArenaDelete {

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.delete",
            aliases = {"deletar"},

            permission = "arena.commands.delete",

            target = CommandTarget.PLAYER
    )
    public void onArenaDelete(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage(Messages.MESSAGE_ARENA_DELETE_INFO.get());
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage(Messages.MESSAGE_ARENA_NOT_EXISTS.get());
            return;
        }

        plugin.getParkourLoadable().destructor(parkour);
        plugin.getParkourManagement().destructor(parkour);

        sender.sendMessage(Messages.MESSAGE_ARENA_DELETE_SUCCESS.get(arenaName));
    }
}
