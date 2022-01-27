package com.rukko.parkour.settings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@RequiredArgsConstructor
public enum Settings {

    DEFAULT_LANG("settings.default-lang"),

    INVENTORY_MATCHES_TITLE("settings.inventories.matches-title"),
    INVENTORY_RANKING_TITLE("settings.inventories.ranking-title"),

    CONNECTION_TYPE("connection.type"),
    CONNECTION_CREDENTIAL_HOST("connection.credential.host"),
    CONNECTION_CREDENTIAL_DB("connection.credential.db"),
    CONNECTION_CREDENTIAL_USER("connection.credential.user"),
    CONNECTION_CREDENTIAL_PASSWORD("connection.credential.password"),

    SCOREBOARD_TITLE("scoreboard.title"),
    SCOREBOARD_CASUAL_ENABLED("scoreboard.casual.enabled"),
    SCOREBOARD_CASUAL_LINES("scoreboard.casual.lines"),
    SCOREBOARD_PARKOUR_ENABLED("scoreboard.parkour.enabled"),
    SCOREBOARD_PARKOUR_LINES("scoreboard.parkour.lines");

    private final String path;

    static final HashMap<String, Object> SETTINGS = new HashMap<>();

    public static void constructor(Settings setting, Object value) {
        SETTINGS.put(setting.getPath(), value);
    }

    public String asString() {
        return get(String.class);
    }

    public Integer asInt() {
        return get(Integer.class);
    }

    public Double asDouble() {
        return get(Double.class);
    }

    public Boolean asBool() {
        return get(Boolean.class);
    }

    public List<String> asListString() {
        return get(List.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T get(Class<T> clazz) {
        final Object value = SETTINGS.get(getPath());

        try {
            if (!clazz.isAssignableFrom(value.getClass()))
                return null;

            return (T) value;
        } catch (Exception ignored) {
            return null;
        }
    }
}
