package com.rukko.parkour.backend.loadable;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface Loadable<S> {

    void load();

    void constructor(S subject);

    void destructor(S subject);
}
