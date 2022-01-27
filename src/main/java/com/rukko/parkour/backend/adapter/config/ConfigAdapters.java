package com.rukko.parkour.backend.adapter.config;

import com.rukko.parkour.backend.adapter.config.impl.CheckpointConfigAdapter;
import com.rukko.parkour.backend.adapter.config.impl.ParkourConfigAdapter;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class ConfigAdapters {

    public static final CheckpointConfigAdapter CHECKPOINT_CONFIG_ADAPTER = new CheckpointConfigAdapter();
    public static final ParkourConfigAdapter PARKOUR_CONFIG_ADAPTER = new ParkourConfigAdapter();
}
