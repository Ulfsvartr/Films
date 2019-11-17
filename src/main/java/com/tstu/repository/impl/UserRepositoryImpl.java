package com.tstu.repository.impl;

import com.tstu.model.User;
import com.tstu.model.enums.Role;
import com.tstu.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static Map<String,User> users = new HashMap<>();


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
    public Optional<User> findById(int id) {
        return users.values().stream()
                .filter(user -> user.getId()==id)
                .findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if(users.containsKey(username)){
            return Optional.of(users.get(username));
        }
        return Optional.empty();
    }

    @Override
    public boolean existByUsername(String username) {
        return users.containsKey(username);
    }

    @Override
    public Optional<User> create(User user) {
        if (!existByUsername(user.getUsername())) {
            users.put(user.getUsername(), user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
