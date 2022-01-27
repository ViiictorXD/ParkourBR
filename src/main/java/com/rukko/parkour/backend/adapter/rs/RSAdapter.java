package com.rukko.parkour.backend.adapter.rs;

import com.rukko.parkour.backend.adapter.Adapter;

import java.sql.ResultSet;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface RSAdapter<T> extends Adapter<ResultSet, T> {


    @Override
    T adapt(ResultSet resultSet);
}
