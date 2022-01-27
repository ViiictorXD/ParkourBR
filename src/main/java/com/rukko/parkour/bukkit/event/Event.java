package com.rukko.parkour.bukkit.event;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public abstract class Event extends org.bukkit.event.Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public void callEvent() {
        Bukkit.getPluginManager().callEvent(this);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
