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
public class CommandArenaDelete {

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.delete",

            permission = "arena.commands.delete",

            target = CommandTarget.PLAYER
    )
    public void onArenaDelete(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage("§cUse '/arena delete <name>' to delete a parkour arena.");
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage("§cA parkour arena with that name doesn't exists.");
            return;
        }

        plugin.getParkourLoadable().destructor(parkour);
        plugin.getParkourManagement().destructor(parkour);

        sender.sendMessage(String.format("§aArena %s deleted.", arenaName));
    }
}
