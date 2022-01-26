package com.rukko.parkour.command.arena;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.utils.Utils;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class CommandArenaAdd {

    private final ParkourPlugin plugin;

    @Command(
            name = "arena.add",

            permission = "arena.commands.add",

            target = CommandTarget.PLAYER
    )
    public void onArenaAdd(Context<Player> context) {
        final Player sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage("§cUse '/arena add <name> <index>' to add a parkour arena checkpoint.");
            return;
        }

        final String arenaName = args[0];
        final Parkour parkour = plugin.getParkourManagement().match(arenaName);
        if (parkour == null) {
            sender.sendMessage("§cA parkour arena with that name doesn't exists.");
            return;
        }

        final Integer index = Utils.parseInt(args[1]);
        if (index == null || index < 0) {
            sender.sendMessage("§cInsert a valid index.");
            return;
        }

        Checkpoint checkpoint = parkour.match(index);

        if (checkpoint == null) parkour.getCheckpoints().add(new Checkpoint(index, sender.getLocation()));
        else checkpoint.setLocation(sender.getLocation());

        plugin.getParkourLoadable().constructor(parkour);

        sender.sendMessage(String.format("§aArena %s updated.", arenaName));
    }
}
