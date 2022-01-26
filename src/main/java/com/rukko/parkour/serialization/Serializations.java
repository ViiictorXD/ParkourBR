package com.rukko.parkour.serialization;

import com.rukko.parkour.serialization.impl.ItemStackVetSerialization;
import com.rukko.parkour.serialization.impl.LocationSerialization;

/**
 * @author ViiictorXD
 */
public class Serializations {

    public static final ItemStackVetSerialization ITEM_STACK_VET_SERIALIZATION = new ItemStackVetSerialization();
    public static final LocationSerialization LOCATION_SERIALIZATION = new LocationSerialization();
}
