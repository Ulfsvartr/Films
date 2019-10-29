package com.tstu.repository;

import com.tstu.model.Role;
import com.tstu.model.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static List<User> users = new ArrayList<>();


    static {
        users.add(new User("Ermak","SSSSS", Role.USER));
        users.add(new User("BigBoss228","sdf", Role.USER));
        users.add(new User("Sasha452","CVBH", Role.USER));
        users.add(new User("Den","adsFG", Role.USER));
        users.add(new User("Fox","SDFG", Role.USER));
    }

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User findById(int id) throws Exception {
        return users.stream()
                .filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(()-> new Exception("Пользователь не найден!"));
    }

    @Override
    public User findByUsername(String username) throws Exception {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()-> new Exception("Пользователь не найден!"));
    }

    @Override
    public boolean existByUsername(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }
}
