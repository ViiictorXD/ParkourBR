package com.rukko.parkour.model;

import com.rukko.parkour.board.FastBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */

@Getter
@AllArgsConstructor
public class Board {

    private Player player;

    @Setter
    private FastBoard board;
}
