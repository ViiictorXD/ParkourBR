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
public class CommandArenaCreate {

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.create",
            aliases = {"criar"},

            permission = "arena.commands.create",

            target = CommandTarget.PLAYER
    )
    public void onArenaCreate(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage(Messages.MESSAGE_ARENA_CREATE_INFO.get());
            return;
        }

        final String arenaName = args[0];
        if (plugin.getParkourManagement().exists(arenaName)) {
            sender.sendMessage(Messages.MESSAGE_ARENA_ALREADY_EXISTS.get());
            return;
        }

        final Parkour parkour = Parkour.newParkour(arenaName);

        plugin.getParkourLoadable().constructor(parkour);
        plugin.getParkourManagement().constructor(parkour);

        sender.sendMessage(Messages.MESSAGE_ARENA_CREATE_SUCCESS.get(arenaName));
    }
}
