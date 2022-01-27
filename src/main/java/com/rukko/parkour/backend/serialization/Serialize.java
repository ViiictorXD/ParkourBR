package com.rukko.parkour.backend.serialization;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface Serialize<K, V> {

    K serialize(V value);

    V deserialize(K key);
}
