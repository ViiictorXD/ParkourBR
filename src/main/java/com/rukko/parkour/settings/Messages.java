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
public enum Messages {

    NONE("messages.none"),

    ITEM_MATCH_EMPTY_NAME("messages.item.match-empty-name"),
    ITEM_MATCH_EMPTY_LORE("messages.item.match-empty-lore"),
    ITEM_MATCH_NAME("messages.item.match-name"),
    ITEM_MATCH_LORE("messages.item.match-lore"),
    ITEM_RANKING_EMPTY_NAME("messages.item.ranking-empty-name"),
    ITEM_RANKING_EMPTY_LORE("messages.item.ranking-empty-lore"),
    ITEM_RANKING_NAME("messages.item.ranking-name"),
    ITEM_RANKING_LORE("messages.item.ranking-lore"),

    MESSAGE_VALID_INDEX("messages.arena.valid-index"),
    MESSAGE_CHECKPOINT_NOT_EXISTS("messages.arena.checkpoint-not-exists"),
    MESSAGE_LOCATION_NOT_EXISTS("messages.arena.location-not-exists"),

    MESSAGE_PARKOUR("messages.parkour.info"),
    MESSAGE_PARKOUR_NOT_IN("messages.parkour.not-in"),
    MESSAGE_PARKOUR_NOT_AVAILABLE("messages.parkour.already-playing"),
    MESSAGE_PARKOUR_ALREADY_PLAYING("messages.parkour.already-playing"),
    MESSAGE_PARKOUR_LEAVE_SUCCESS("messages.parkour.leave-success"),
    MESSAGE_PARKOUR_START_INFO("messages.parkour.start-info"),
    MESSAGE_PARKOUR_START_SUCCESS("messages.parkour.start-success"),

    MESSAGE_ARENA("messages.arena.info"),
    MESSAGE_ARENA_NOT_EXISTS("messages.arena.not-exists"),
    MESSAGE_ARENA_ALREADY_EXISTS("messages.arena.already-exists"),
    MESSAGE_ARENA_ADD_INFO("messages.arena.add-info"),
    MESSAGE_ARENA_ADD_SUCCESS("messages.arena.add-success"),
    MESSAGE_ARENA_CREATE_INFO("messages.arena.create-info"),
    MESSAGE_ARENA_CREATE_SUCCESS("messages.arena.create-success"),
    MESSAGE_ARENA_DELETE_INFO("messages.arena.delete-info"),
    MESSAGE_ARENA_DELETE_SUCCESS("messages.arena.delete-success"),
    MESSAGE_ARENA_REMOVE_INFO("messages.arena.remove-info"),
    MESSAGE_ARENA_REMOVE_SUCCESS("messages.arena.remove-success"),
    MESSAGE_ARENA_SET_INFO("messages.arena.set-info"),
    MESSAGE_ARENA_SET_SUCCESS("messages.arena.set-success");

    private final String path;

    static final HashMap<String, Object> MESSAGES = new HashMap<>();

    public static void constructor(Messages message, Object value) {
        if (value instanceof String) {
            value = ChatColor.translateAlternateColorCodes('&', (String) value);
        } else if (value instanceof List) {
            final List<String> list = (List<String>) value;
            list.replaceAll(operator -> ChatColor.translateAlternateColorCodes('&', operator));
        }

        MESSAGES.put(message.getPath(), value);
    }

    public String get() {
        return get(new Object[0]);
    }

    public List<String> asList() {
        return get(List.class);
    }

    public String get(Object... objects) {
        final String pattern = (String) MESSAGES.get(getPath());
        if (objects != null && objects.length > 0)
            return MessageFormat.format(pattern, objects);

        return pattern;
    }

    @SuppressWarnings("unchecked")
    private <T> T get(Class<T> clazz) {
        final Object value = MESSAGES.get(getPath());

        try {
            if (!clazz.isAssignableFrom(value.getClass()))
                return null;

            return (T) value;
        } catch (Exception ignored) {
            return null;
        }
    }
}
