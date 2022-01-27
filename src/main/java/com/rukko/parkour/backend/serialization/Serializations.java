package com.rukko.parkour.backend.serialization;

import com.rukko.parkour.backend.serialization.impl.ItemStackVetSerialization;
import com.rukko.parkour.backend.serialization.impl.LocationSerialization;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class Serializations {

    public static final ItemStackVetSerialization ITEM_STACK_VET_SERIALIZATION = new ItemStackVetSerialization();
    public static final LocationSerialization LOCATION_SERIALIZATION = new LocationSerialization();
}
