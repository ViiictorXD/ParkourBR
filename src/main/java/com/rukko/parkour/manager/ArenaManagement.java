package com.rukko.parkour.manager;

import com.rukko.parkour.event.arena.ArenaEndEvent;
import com.rukko.parkour.event.arena.ArenaStartEvent;
import com.rukko.parkour.model.arena.Arena;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author ViiictorXD
 */

@Getter
public class ArenaManagement {

    private final Set<Arena> arenas = new HashSet<>();

    public Arena match(UUID uniqueId) {
        return arenas.stream().filter(arena -> arena.getUniqueId().equals(uniqueId)).findAny().orElse(null);
    }

    public boolean exists(UUID uniqueId) {
        return match(uniqueId) != null;
    }

    public void constructor(Arena arena) {
        arenas.add(arena);

        new ArenaStartEvent(arena).callEvent();
    }

    public void destructor(Arena arena) {
        new ArenaEndEvent(arena).callEvent();

        arena.stop();

        arenas.remove(arena);
    }
}
