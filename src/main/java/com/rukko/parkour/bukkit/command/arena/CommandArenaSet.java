package com.rukko.parkour.bukkit.command.arena;

import com.google.common.collect.ImmutableList;
import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.settings.Messages;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@RequiredArgsConstructor
public class CommandArenaSet {

    private static final ImmutableList<String> LOCATIONS_NAME = ImmutableList.of("entry", "exit", "end");

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.set",
            aliases = {"setar"},

            permission = "arena.commands.set",

            target = CommandTarget.PLAYER
    )
    public void onArenaSet(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage(Messages.MESSAGE_ARENA_SET_INFO.get());
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage(Messages.MESSAGE_ARENA_NOT_EXISTS.get());
            return;
        }

        final String locationName = StringUtils.lowerCase(args[1]);
        if (!LOCATIONS_NAME.contains(locationName)) {
            sender.sendMessage(Messages.MESSAGE_LOCATION_NOT_EXISTS.get());
            return;
        }

        switch (args[1]) {
            case "entry":
                parkour.setEntry(sender.getLocation());
                break;
            case "exit": {
                parkour.setExit(sender.getLocation());
                break;
            }
            case "end": {
                parkour.setEnd(sender.getLocation());
                break;
            }
        }

        plugin.getParkourLoadable().constructor(parkour);

        sender.sendMessage(Messages.MESSAGE_ARENA_SET_SUCCESS.get(arenaName));
    }
}
