package com.rukko.parkour.backend;

import com.rukko.parkour.ParkourPlugin;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
public class SimpleConfiguration {

    private static final ParkourPlugin PLUGIN = ParkourPlugin.getPlugin();

    private final File file;
    private final FileConfiguration configuration;

    public SimpleConfiguration(String local) {
        file = new File(PLUGIN.getDataFolder(), local);

        try {
            PLUGIN.saveResource(local, false);
        } catch (Exception ignored) { }

        configuration = YamlConfiguration.loadConfiguration(file);

        save();
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void delete() {
        file.delete();
    }
}
