package com.tstu.repository;

import com.tstu.model.User;

public interface UserRepository {
    User findById(int id) throws Exception;
    User findByUsername(String name) throws Exception;
    boolean existByUsername(String name);
    User create(User user);
}
