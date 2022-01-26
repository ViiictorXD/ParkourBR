package com.rukko.parkour.adapter.config.impl;

import com.rukko.parkour.adapter.config.ConfigAdapter;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.serialization.Serializations;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ViiictorXD
 */
public class CheckpointConfigAdapter implements ConfigAdapter<List<Checkpoint>> {

    @Override
    public List<Checkpoint> adapt(FileConfiguration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("checkpoints");
        if (section == null)
            return new ArrayList<>();

        final List<Checkpoint> checkpoints = new ArrayList<>();

        for (String key : section.getKeys(false)) {
            final int index = section.getInt(key + ".index");
            final Location location = Serializations.LOCATION_SERIALIZATION.deserialize(section.getString(key + ".location"));

            checkpoints.add(new Checkpoint(index, location));
        }
        return checkpoints;
    }
}
