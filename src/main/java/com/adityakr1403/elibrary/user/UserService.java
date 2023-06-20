package com.adityakr1403.elibrary.user;

import java.util.List;

public interface UserService {
    List<UserRecord> getAllUsers();

    UserRecord addUser(User user);

    UserRecord getUserByEmail(String email);

    void deleteUserByEmail(String email);

    UserRecord update(User user);
}
