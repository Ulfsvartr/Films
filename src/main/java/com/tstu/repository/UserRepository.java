package com.tstu.repository;

import com.tstu.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(int id);

    Optional<User> findByUsername(String name);

    boolean existByUsername(String name);

    Optional<User> create(User user);
}
