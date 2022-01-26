package com.rukko.parkour.event;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

/**
 * @author ViiictorXD
 */
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
