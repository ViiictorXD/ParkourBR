package com.rukko.parkour.adapter.rs;

import com.rukko.parkour.adapter.Adapter;

import java.sql.ResultSet;

/**
 * @author ViiictorXD
 */
public interface RSAdapter<T> extends Adapter<ResultSet, T> {


    @Override
    T adapt(ResultSet resultSet);
}
