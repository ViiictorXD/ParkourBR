package com.rukko.parkour.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ViiictorXD
 */

@Getter
@Setter
@AllArgsConstructor
public class Parkour {

    private String realName;

    private Location entry;
    private Location exit;

    private Location end;

    private List<Checkpoint> checkpoints;

    public static Parkour newParkour(String realName) {
        return new Parkour(realName, null, null, null, new ArrayList<>());
    }

    public Checkpoint match(int index) {
        return checkpoints.stream().filter(checkpoint -> checkpoint.getIndex() == index).findAny().orElse(null);
    }

    public Checkpoint match(Location location) {
        return checkpoints.stream().filter(checkpoint -> checkpoint.isNearEnough(location)).findAny().orElse(null);
    }

    public Checkpoint first() {
        return checkpoints.stream().sorted(Comparator.comparingInt(Checkpoint::getIndex)).findAny().orElse(null);
    }

    public boolean exists(int index) {
        return match(index) != null;
    }

    public boolean isNearEnd(Location location) {
        return end.distanceSquared(location) <= 0.4;
    }

    public boolean available() {
        return checkpoints.size() > 0 && entry != null && exit != null && end != null;
    }
}
