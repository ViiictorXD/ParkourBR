package com.rukko.parkour.command.arena;

import com.google.common.collect.ImmutableList;
import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.Parkour;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class CommandArenaSet {

    private static final ImmutableList<String> LOCATIONS_NAME = ImmutableList.of("entry", "exit", "end");

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.set",

            permission = "arena.commands.set",

            target = CommandTarget.PLAYER
    )
    public void onArenaSet(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage("§cUse '/arena set <name> <location name>' to set parkour arena locations.");
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage("§cA parkour arena with that name doesn't exists.");
            return;
        }

        final String locationName = StringUtils.lowerCase(args[1]);
        if (!LOCATIONS_NAME.contains(locationName)) {
            sender.sendMessage("§cA location with that name doesn't exists. Available: entry, exit and end");
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

        sender.sendMessage(String.format("§aArena %s updated.", arenaName));
    }
}
