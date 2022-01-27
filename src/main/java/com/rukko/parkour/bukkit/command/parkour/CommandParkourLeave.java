package com.rukko.parkour.bukkit.command.parkour;

import com.rukko.parkour.ParkourPlugin;
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
public class CommandParkourLeave {

    private final ParkourPlugin plugin;

    @Command(
            name = "parkour.leave",
            aliases = {"sair"},

            permission = "parkour.commands.leave",

            target = CommandTarget.PLAYER
    )
    public void onParkourLeave(Context<Player> context) {
        final Player sender = context.getSender();

        final Arena arena = plugin.getArenaManagement().match(sender.getUniqueId());
        if (arena == null) {
            sender.sendMessage(Messages.MESSAGE_PARKOUR_NOT_IN.get());
            return;
        }

        plugin.getArenaManagement().destructor(arena);

        arena.stop();

        sender.teleport(arena.getParkour().getExit());
        sender.sendMessage(Messages.MESSAGE_PARKOUR_LEAVE_SUCCESS.get());
    }
}
