package com.rukko.parkour.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ViiictorXD
 */

@Getter
@AllArgsConstructor
public class ConnectionCredential {

    private String host;
    private String db;
    private String name;
    private String pass;
}
