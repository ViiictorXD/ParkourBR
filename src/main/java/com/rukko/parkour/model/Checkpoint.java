package com.rukko.parkour.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

/**
 * @author ViiictorXD
 */

@Getter
@AllArgsConstructor
public class Checkpoint {

    private int index;
    @Setter
    private Location location;

    public boolean isNearEnough(Location location) {
        return location.distanceSquared(this.location) <= 0.4;
    }
}
