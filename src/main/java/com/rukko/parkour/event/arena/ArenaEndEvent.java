package com.rukko.parkour.event.arena;

import com.rukko.parkour.event.Event;
import com.rukko.parkour.model.arena.Arena;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ViiictorXD
 */

@Getter
@RequiredArgsConstructor
public class ArenaEndEvent extends Event {

    private final Arena arena;
}
