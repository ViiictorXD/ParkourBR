package com.rukko.parkour.backend.serialization.impl;

import com.rukko.parkour.backend.serialization.Serialize;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class ItemStackVetSerialization implements Serialize<String, ItemStack[]> {

    @Override
    public String serialize(ItemStack[] value) {
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)
        ) {
            dataOutput.writeInt(value.length);

            for (ItemStack itemStack : value)
                dataOutput.writeObject(itemStack);

            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException ignored) {
            return null;
        }
    }

    @Override
    public ItemStack[] deserialize(String key) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(key));
                BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)
        ) {
            final ItemStack[] items = new ItemStack[dataInput.readInt()];

            for (int index = 0; index < items.length; index++) {
                items[index] = (ItemStack) dataInput.readObject();
            }

            return items;
        } catch (IOException | ClassNotFoundException ignored) {
            return null;
        }
    }
}
