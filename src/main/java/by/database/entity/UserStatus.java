package by.database.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {
    ADMIN, MANAGER;

    public static Optional<UserStatus> find(String status){
        return Arrays.stream(values())
                .filter(user-> user.name().equals(status))
                .findFirst();
    }
}
