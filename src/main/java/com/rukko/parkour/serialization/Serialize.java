package com.rukko.parkour.serialization;

/**
 * @author ViiictorXD
 */
public interface Serialize<K, V> {

    K serialize(V value);

    V deserialize(K key);
}
