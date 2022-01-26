package com.rukko.parkour.adapter;

/**
 * @author ViiictorXD
 */
public interface Adapter<F, T> {

    T adapt(F from);
}
