package com.rukko.parkour.model;

import lombok.Getter;

/**
 * @author ViiictorXD
 */

@Getter
public enum Content {

    PARKOUR(
            "",
            " §aCheckpoint: §f",
            "   §f{arena_checkpoint_pattern}",
            " §aElapsed time: ",
            "   §f{arena_elapsed_pattern}",
            "",
            "§2DevRoom Services"
    ),
    MATCH(
            "",
            "",
            " &aBest Attempt:",
            "   &f{user_best_attempt}",
            "",
            "",
            "",
            "§2DevRoom Services"
    );

    private final String[] lines;

    Content(String... lines) {
        this.lines = lines;
    }
}
