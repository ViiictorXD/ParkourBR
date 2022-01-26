package com.rukko.parkour.loadable;

/**
 * @author ViiictorXD
 */
public interface Loadable<S> {

    void load();

    void constructor(S subject);

    void destructor(S subject);
}
