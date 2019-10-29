package com.tstu.service;

import com.tstu.model.User;

public interface UserService {
    User login(String username, String password) throws Exception;

    User logout();

    User register(String username, String password) throws Exception;
}
