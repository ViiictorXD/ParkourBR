package com.rukko.parkour.manager;

import com.rukko.parkour.model.user.User;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author ViiictorXD
 */

@Getter
public class UserManagement {

    private final Set<User> users = new HashSet<>();

    public User match(UUID uniqueId) {
        return users.stream().filter(user -> user.getUniqueId().equals(uniqueId)).findAny().orElse(null);
    }

    public void constructor(User user) {
        users.add(user);
    }

    public boolean exists(UUID uniqueId) {
        return match(uniqueId) != null;
    }
}
