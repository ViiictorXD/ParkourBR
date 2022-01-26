package com.rukko.parkour.command.parkour;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */
public class CommandParkour {

    @Command(
            name = "parkour",

            permission = "parkour.commands.use",

            target = CommandTarget.PLAYER
    )
    public void onParkour(Context<Player> context) {
        context.sendMessage(new String[] {
                "",
                "§a/parkour start <arena name> §7- Start parkour.",
                "§a/parkour leave §7- Leave of parkour.",
                "§a/parkour matches §7- Your matches.",
                "§a/parkour ranking §7- Parkour end time ranking.",
                ""
        });
    }
}
