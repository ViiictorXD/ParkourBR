package com.rukko.parkour.backend.connection;

import lombok.SneakyThrows;

import java.sql.Connection;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public interface ConnectionFactory {

    Connection getConnection();

    void connect();

    @SneakyThrows
    default void disconnect() {
        if (getConnection() == null || getConnection().isClosed())
            return;

        getConnection().close();
    }

    @SneakyThrows
    default boolean hasConnection() {
        return getConnection() != null && !getConnection().isClosed();
    }
}
