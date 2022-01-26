package com.rukko.parkour.connection;

import lombok.SneakyThrows;

import java.sql.Connection;

/**
 * @author ViiictorXD
 */
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
