package com.rukko.parkour.backend.adapter.config.impl;

import com.rukko.parkour.backend.adapter.config.ConfigAdapter;
import com.rukko.parkour.backend.adapter.config.ConfigAdapters;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.backend.serialization.Serializations;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class ParkourConfigAdapter implements ConfigAdapter<Parkour> {

    @Override
    public Parkour adapt(FileConfiguration configuration) {
        final String realName = configuration.getString("realName");

        final Location entry = Serializations.LOCATION_SERIALIZATION.deserialize(configuration.getString("locations.entry"));
        final Location exit = Serializations.LOCATION_SERIALIZATION.deserialize(configuration.getString("locations.exit"));

        final Location end = Serializations.LOCATION_SERIALIZATION.deserialize(configuration.getString("locations.end"));

        final List<Checkpoint> checkpoints = ConfigAdapters.CHECKPOINT_CONFIG_ADAPTER.adapt(configuration);

        return new Parkour(realName, entry, exit, end, checkpoints);
    }
}
