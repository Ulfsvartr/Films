package com.tstu.repository.jdbc;

import com.tstu.model.User;
import com.tstu.model.enums.Role;
import com.tstu.repository.UserRepository;
import com.tstu.utils.DbConstants;
import com.tstu.utils.UserConstants;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImplJDBC implements UserRepository {

    private static UserRepository instance;
    private Connection connection;
    private PreparedStatement preparedStatement;

    private UserRepositoryImplJDBC() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImplJDBC();
        }
        return instance;
    }

    @Override
    public Optional<User> findById(int id) {
        User user = UserConstants.guest;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            preparedStatement = connection.prepareStatement("SELECT users.* FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.USER);
            }
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
            }
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByUsername(String name) {

        User user = UserConstants.guest;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            preparedStatement = connection.prepareStatement("SELECT users.* FROM users WHERE username = ?");
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.USER);
            }
        } catch (SQLException e) {
            Optional.empty();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
            }
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
        }
        return Optional.of(user);
    }

    @Override
    public boolean existByUsername(String name) {
        return findByUsername(name).isPresent();
    }

    @Override
    public Optional<User> create(User user) {
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            preparedStatement = connection.prepareStatement("INSERT INTO users(username,password) VALUES(?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            int count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
        }
        return Optional.of(user);
    }
}
