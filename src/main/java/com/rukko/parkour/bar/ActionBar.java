package com.rukko.parkour.bar;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author ViiictorXD
 */
public class ActionBar {

    public static void send(Player player, String message) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;

        final IChatBaseComponent chatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        final EntityPlayer handle = craftPlayer.getHandle();

        handle.playerConnection.sendPacket(new PacketPlayOutChat(chatBaseComponent, (byte) 2));
    }
}
