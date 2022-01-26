package com.rukko.parkour.loadable.impl;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.SimpleConfiguration;
import com.rukko.parkour.adapter.config.ConfigAdapters;
import com.rukko.parkour.loadable.Loadable;
import com.rukko.parkour.model.Checkpoint;
import com.rukko.parkour.model.Parkour;
import com.rukko.parkour.serialization.Serializations;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author ViiictorXD
 */

@RequiredArgsConstructor
public class ParkourLoadable implements Loadable<Parkour> {

    private final ParkourPlugin plugin;

    @Override
    public void load() {
        final File[] files = new File(plugin.getDataFolder(), "parkour").listFiles();

        if (files == null || files.length == 0)
            return;

        for (File file : files) {
            final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            plugin.getParkourManagement().constructor(ConfigAdapters.PARKOUR_CONFIG_ADAPTER.adapt(configuration));
        }
    }

    @Override
    public void constructor(Parkour subject) {
        final String local = StringUtils.lowerCase("parkour/" + StringUtils.lowerCase(subject.getRealName()) + ".yml");

        final SimpleConfiguration simpleConfiguration = new SimpleConfiguration(local);
        final FileConfiguration configuration = simpleConfiguration.getConfiguration();
        configuration.set("checkpoints", null);

        configuration.set("realName", subject.getRealName());

        for (Checkpoint checkpoint : subject.getCheckpoints()) {
            final String checkpointLocal = "checkpoints.checkpoint_" + checkpoint.getIndex();

            configuration.set(checkpointLocal + ".index", checkpoint.getIndex());
            configuration.set(checkpointLocal + ".location", Serializations.LOCATION_SERIALIZATION.serialize(checkpoint.getLocation()));
        }

        configuration.set("locations.entry", Serializations.LOCATION_SERIALIZATION.serialize(subject.getEntry()));
        configuration.set("locations.exit", Serializations.LOCATION_SERIALIZATION.serialize(subject.getExit()));

        configuration.set("locations.end", Serializations.LOCATION_SERIALIZATION.serialize(subject.getEnd()));

        simpleConfiguration.save();
    }

    @Override
    public void destructor(Parkour subject) {
        final String local = StringUtils.lowerCase("parkour/" + StringUtils.lowerCase(subject.getRealName()) + ".yml");

        final SimpleConfiguration simpleConfiguration = new SimpleConfiguration(local);
        simpleConfiguration.delete();
    }
}
