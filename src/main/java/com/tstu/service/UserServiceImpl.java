package com.tstu.service;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.User;
import com.tstu.model.enums.Role;
import com.tstu.repository.UserRepository;
import com.tstu.repository.jdbc.UserRepositoryImplJDBC;
import com.tstu.utils.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
    public User login(String username, String password) throws MovieLibraryException {
        if(username == null || password == null)  {
            throw new MovieLibraryException(MovieLibraryError.USERNAME_OR_PASSWORD_EMPTY);
        }
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new MovieLibraryException(MovieLibraryError.USER_NOT_FOUND));
        if (foundUser.getPassword().equals(password)) {
            return foundUser;
        } else {
            throw new MovieLibraryException(MovieLibraryError.PASSWORD_NOT_MATCH);
        }
    }

    @Override
    public User logout() {
        return UserConstants.guest;
    }

    @Override
    public User register(String username, String password) throws MovieLibraryException {
        if (userRepository.existByUsername(username)) {
            throw new MovieLibraryException(MovieLibraryError.USERNAME_IS_BUSY);
        }
        User user = new User(username, password, Role.USER);
        userRepository.create(user);
        return user;
    }
}
