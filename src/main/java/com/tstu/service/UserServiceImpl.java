package com.tstu.service;

import com.tstu.model.Role;
import com.tstu.model.User;
import com.tstu.model.UserConstants;
import com.tstu.repository.UserRepository;
import com.tstu.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    private static UserService instance;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User login(String username, String password) throws Exception {
        User foundUser = userRepository.findByUsername(username);
        if (foundUser.getPassword().equals(password)) {
            return foundUser;
        } else {
            throw new Exception("Пароль не совпадает.");
        }
    }

    @Override
    public User logout() {
        return UserConstants.guest;
    }

    @Override
    public User register(String username, String password) throws Exception {
        if(userRepository.existByUsername(username)){
            throw new Exception("Пользователь с данным именем уже существует.");
        }
        User user=new User(username,password, Role.USER);
        userRepository.create(user);
        return user;
    }
}
