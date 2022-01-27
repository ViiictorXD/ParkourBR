package com.rukko.parkour.manager;

import com.rukko.parkour.model.Parkour;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
public class ParkourManagement {

    private final Set<Parkour> parkours = new HashSet<>();

    public Parkour match(String realName) {
        return parkours.stream().filter(parkour -> parkour.getRealName().equalsIgnoreCase(realName)).findAny().orElse(null);
    }

    public boolean exists(String realName) {
        return match(realName) != null;
    }

    public void constructor(Parkour parkour) {
        parkours.add(parkour);
    }

    public void destructor(Parkour parkour) {
        parkours.remove(parkour);
    }
}
