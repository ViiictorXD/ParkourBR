package com.rukko.parkour.serialization.impl;

import com.rukko.parkour.serialization.Serialize;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author ViiictorXD
 */
public class LocationSerialization implements Serialize<String, Location> {

    @Override
    public String serialize(Location value) {
        try {
            return value.getWorld().getName()
                    + ":" + value.getX()
                    + ":" + value.getY()
                    + ":" + value.getZ()
                    + ":" + value.getYaw()
                    + ":" + value.getPitch();
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public Location deserialize(String key) {
        try {
            return new Location(
                    Bukkit.getWorld(key.split(":")[0]),
                    Double.parseDouble(key.split(":")[1]),
                    Double.parseDouble(key.split(":")[2]),
                    Double.parseDouble(key.split(":")[3]),
                    Float.parseFloat(key.split(":")[4]),
                    Float.parseFloat(key.split(":")[5])
            );
        } catch (Exception ignored) {
            return null;
        }
    }
}
