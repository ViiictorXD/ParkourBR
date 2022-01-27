package com.rukko.parkour.backend.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
@AllArgsConstructor
public class ConnectionCredential {

    private String host;
    private String db;
    private String name;
    private String pass;
}
