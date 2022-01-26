package com.rukko.parkour.command.arena;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.Parkour;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class CommandArenaCreate {

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.create",

            permission = "arena.commands.create",

            target = CommandTarget.PLAYER
    )
    public void onArenaCreate(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage("§cUse '/arena create <name>' to create a parkour arena.");
            return;
        }

        final String arenaName = args[0];
        if (plugin.getParkourManagement().exists(arenaName)) {
            sender.sendMessage("§cA parkour arena with that name already exists.");
            return;
        }

        final Parkour parkour = Parkour.newParkour(arenaName);

        plugin.getParkourLoadable().constructor(parkour);
        plugin.getParkourManagement().constructor(parkour);

        sender.sendMessage(String.format("§aArena %s created.", arenaName));
    }
}
