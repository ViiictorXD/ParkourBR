package com.rukko.parkour.model.user.match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

/**
 * @author ViiictorXD
 */

@Getter
@RequiredArgsConstructor
public enum MatchResult {

    COMPLETED("Completed", ChatColor.GREEN),
    DESIST("Uncompleted", ChatColor.RED);

    private final String name;
    private final ChatColor color;

    public String beautifulName() {
        return color + name;
    }
}
