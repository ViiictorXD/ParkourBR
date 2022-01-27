package com.rukko.parkour.backend.holder.impl;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.backend.holder.Holder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@RequiredArgsConstructor
public class ConfigHolder implements Holder {

    private final ParkourPlugin plugin;

    @Override
    public void setup() {

    }
}
