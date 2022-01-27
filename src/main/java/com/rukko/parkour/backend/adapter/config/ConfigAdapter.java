package com.rukko.parkour.backend.adapter.config;

import com.rukko.parkour.backend.adapter.Adapter;
import org.bukkit.configuration.file.FileConfiguration;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface ConfigAdapter<T> extends Adapter<FileConfiguration, T> {

    @Override
    T adapt(FileConfiguration configuration);
}
