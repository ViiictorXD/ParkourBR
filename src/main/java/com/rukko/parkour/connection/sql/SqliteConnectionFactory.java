package com.rukko.parkour.connection.sql;

import com.rukko.parkour.connection.ConnectionFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ViiictorXD
 */

@Getter
@RequiredArgsConstructor
public class SqliteConnectionFactory implements ConnectionFactory {

    private final File file;
    private final String name;

    private Connection connection;

    @Override
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + file + "/" + name + ".db");
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean hasConnection() {
        try {
            return getConnection() != null && !getConnection().isClosed();
        } catch (SQLException exception) {
            return false;
        }
    }
}
