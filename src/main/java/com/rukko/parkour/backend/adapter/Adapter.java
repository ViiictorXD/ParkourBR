package com.rukko.parkour.backend.adapter;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface Adapter<F, T> {

    T adapt(F from);
}
