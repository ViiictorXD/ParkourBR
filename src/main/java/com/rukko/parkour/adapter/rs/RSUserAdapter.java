package com.rukko.parkour.adapter.rs;

import com.rukko.parkour.model.user.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.UUID;

/**
 * @author ViiictorXD
 */
public class RSUserAdapter implements RSAdapter<User>  {

    @SneakyThrows
    @Override
    public User adapt(ResultSet resultSet) {
        final UUID uniqueId = UUID.fromString(resultSet.getString("uniqueId"));
        final String realName = resultSet.getString("realName");

        return new User(uniqueId, realName);
    }
}
