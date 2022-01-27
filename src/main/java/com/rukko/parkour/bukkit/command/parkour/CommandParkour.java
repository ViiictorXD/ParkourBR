package com.rukko.parkour.bukkit.command.parkour;

import com.rukko.parkour.settings.Messages;
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
public class CommandParkour {

    @Command(
            name = "parkour",

            permission = "parkour.commands.use",

            target = CommandTarget.PLAYER
    )
    public void onParkour(Context<Player> context) {
        context.sendMessage(Messages.MESSAGE_PARKOUR.get());
    }
}
