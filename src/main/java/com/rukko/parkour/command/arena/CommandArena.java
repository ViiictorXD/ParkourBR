package com.rukko.parkour.command.arena;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */
public class CommandArena {

    @Command(
            name = "arena",

            permission = "arena.commands.use",

            target = CommandTarget.PLAYER
    )
    public void onArena(Context<Player> context) {
        context.sendMessage(new String[] {
                "",
                "§a/arena create <name> §7- Create a parkour arena.",
                "§a/arena delete <name> §7- Delete a parkour arena.",
                "§a/arena set <name> <entry, exit, end> §7- Set parkour locations.",
                "§a/arena add <name> <index> §7- Add a parkour checkpoint.",
                "§a/arena remove <name> <index> §7- Remove a parkour checkpoint.",
                ""
        });
    }
}
