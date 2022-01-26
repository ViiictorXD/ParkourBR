package com.rukko.parkour.adapter.config;

import com.rukko.parkour.adapter.Adapter;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author ViiictorXD
 */
public interface ConfigAdapter<T> extends Adapter<FileConfiguration, T> {

    @Override
    T adapt(FileConfiguration configuration);
}
