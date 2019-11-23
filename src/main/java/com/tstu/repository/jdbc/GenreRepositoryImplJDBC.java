package com.tstu.repository.jdbc;

import com.tstu.model.Genre;
import com.tstu.repository.GenreRepository;
import com.tstu.utils.DbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryImplJDBC implements GenreRepository {

    private static GenreRepository instance;

    private Connection connection;
    private PreparedStatement preparedStatement;

    private GenreRepositoryImplJDBC() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static GenreRepository getInstance() {
        if (instance == null) {
            instance = new GenreRepositoryImplJDBC();
        }
        return instance;
    }

    @Override
    public Optional<Genre> findByName(String name) {
        List<Genre> genres = null;
        Genre genre = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            String sql = "SELECT * FROM genres WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                genre = new Genre(rs.getLong("id"), rs.getString("name"));
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
        return Optional.of(genre);
    }
}
