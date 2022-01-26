package com.rukko.parkour;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ViiictorXD
 */

@Getter
public class ParkourBoard {

    private final String title = "§6§lPARKOUR";

    private final List<String> casualLines = new ArrayList<>();
    private final List<String> parkourLines = new ArrayList<>();

    public void load() {
        casualLines.addAll(Arrays.asList(
                "",
                " §aBest Attempt: §f{attempt_pattern}",
                "",
                " §aLeaderboard:",
                "  §2#1 {ranking_name_1}: §f{ranking_elapsed_1}",
                "  §2#2 {ranking_name_2}: §f{ranking_elapsed_2}",
                "  §2#3 {ranking_name_3}: §f{ranking_elapsed_3}",
                "  §2#4 {ranking_name_4}: §f{ranking_elapsed_4}",
                "  §2#5 {ranking_name_5}: §f{ranking_elapsed_5}",
                "",
                "§2DevRoom Services"
        ));

        parkourLines.addAll(Arrays.asList(
                "",
                " §aCheckpoint:",
                "   §f{checkpoint_pattern}",
                " §aElapsed time: ",
                "   §f{elapsed_pattern}",
                "",
                "§2DevRoom Services"
        ));
    }
}
