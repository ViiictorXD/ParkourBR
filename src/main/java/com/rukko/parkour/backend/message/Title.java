package com.rukko.parkour.backend.message;

import org.bukkit.ChatColor;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class Title {

    public static final Title EMPTY = new Title("", "", 0, 0, 0);

    public final String title, subtitle;
    public final int start, duration, end;

    public Title(String title, String subtitle, int start, int duration, int end) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        this.start = start;
        this.duration = duration;
        this.end = end;
    }
}
