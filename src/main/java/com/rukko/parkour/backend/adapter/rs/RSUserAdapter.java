package com.rukko.parkour.backend.adapter.rs;

import com.rukko.parkour.model.user.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.UUID;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class RSUserAdapter implements RSAdapter<User>  {

    @SneakyThrows
    @Override
    public User adapt(ResultSet resultSet) {
        final UUID uniqueId = UUID.fromString(resultSet.getString("uniqueId"));
        final String realName = resultSet.getString("realName");

        return new User(uniqueId, realName);
    }
}
