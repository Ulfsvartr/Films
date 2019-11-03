package com.tstu.repository;

import com.tstu.model.Role;
import com.tstu.model.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    public static Map<String,User> users = new HashMap<>();


    static {
        users.put("Ermak",new User("Ermak","Ermak", Role.USER));
        users.put("BigBoss",new User("BigBoss","Big", Role.USER));
        users.put("Sasha452",new User("Sasha452","Sasha", Role.USER));
        users.put("Den",new User("Den","den", Role.USER));
        users.put("Fox",new User("Fox","foxtrot", Role.USER));
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
        return users.values().stream()
                .filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(()-> new Exception("Пользователь не найден!"));
    }

    @Override
    public User findByUsername(String username) throws Exception {
        if(users.containsKey(username)){
            return users.get(username);
        }
        else{
            throw new Exception("Пользователь не найден!");
        }
    }

    @Override
    public boolean existByUsername(String username) {
        return users.containsKey(username);
    }

    @Override
    public User create(User user) throws Exception {
        if (!existByUsername(user.getUsername())) {
            users.put(user.getUsername(), user);
            return user;
        } else {
            throw new Exception("Пользователь с данным именем уже существует!");
        }
    }
}
