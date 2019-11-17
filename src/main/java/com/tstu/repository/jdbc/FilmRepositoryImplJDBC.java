package com.tstu.repository.jdbc;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.repository.FilmRepository;
import com.tstu.utils.DbConstants;
import com.tstu.utils.FilmHelper;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FilmRepositoryImplJDBC implements FilmRepository {

    private static FilmRepository instance;
    private Connection connection;
    private PreparedStatement preparedStatement;

    private FilmRepositoryImplJDBC() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepositoryImplJDBC();
        }
        return instance;
    }

    @Override
    public Optional<Film> findById(String imdbId) throws MovieLibraryException {
        Film film = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            String sql = "SELECT films.*, GROUP_CONCAT(genres.name) AS genre FROM films " +
                    "INNER JOIN film_generes ON films.imdbId = film_generes.film_id " +
                    "LEFT JOIN genres ON film_generes.genre_id = genres.id WHERE imdbId = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, imdbId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                film = FilmHelper.buildFilm(
                        rs.getString("imdbId"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getString("releaseDate"),
                        DateTimeFormatter.ISO_LOCAL_DATE);
                film.addReviews(getFilmReviews(film.getImdbId()));
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
        return Optional.of(film);
    }

    @Override
    public List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException {
        List<Film> films = new ArrayList<>();
        Film film;
        ResultSet rs = null;
        Map<String, Object> params = buildParamMap(name, imdbId, type, releaseDate);
        String sql = buildQuery(params);

        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            preparedStatement = getPreparedStatement(params, sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                film = FilmHelper.buildFilm(
                        rs.getString("imdbId"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getString("releaseDate"),
                        DateTimeFormatter.ISO_LOCAL_DATE);
                film.addReviews(getFilmReviews(film.getImdbId()));
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return films;
    }

    @Override
    public Review saveReview(Film film, User user, Review review) {
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            preparedStatement = connection.prepareStatement("INSERT INTO reviews(author,text,rating,postDate,film_id) VALUES(?,?,?,?,?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getData().toString());
            preparedStatement.setString(5, film.getImdbId());
            int count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        return review;
    }


    private PreparedStatement getPreparedStatement(Map<String, Object> params, String sql) throws SQLException {
        PreparedStatement stmt = null;
        stmt = connection.prepareStatement(sql);
        int paramNumber = 1;
        for (String paramName : params.keySet()) {
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                stmt.setString(paramNumber++, "%" + paramValue.toString() + "%");
            }
        }
        return stmt;
    }

    private Map<String, Object> buildParamMap(String name, String imdbId, String type, String releaseDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("films.name", name);
        params.put("imdbId", imdbId);
        params.put("type", type);
        params.put("releaseDate", releaseDate);
        return params;
    }

    private String buildQuery(Map<String, Object> params) {
        StringBuilder sql = new StringBuilder("SELECT films.*, GROUP_CONCAT(genres.name) AS genre FROM films " +
                "INNER JOIN film_generes ON films.imdbId = film_generes.film_id " +
                "LEFT JOIN genres ON film_generes.genre_id = genres.id ");
        boolean first = true;

        for (String paramName : params.keySet()) {
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                if (first) {
                    sql.append(" WHERE ");
                    first = false;
                } else {
                    sql.append(" AND ");
                }
                sql.append(paramName).append(" LIKE ?");
            }
        }

        sql.append(" GROUP BY films.imdbId ");

        return sql.toString();
    }

    private List<Review> getFilmReviews(String imdbId) {
        List<Review> reviews = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            preparedStatement = connection.prepareStatement("SELECT reviews.id,text,rating,postDate,users.username FROM reviews " +
                    "INNER JOIN users ON reviews.author = users.id " +
                    "WHERE film_id = ? ");
            preparedStatement.setString(1, imdbId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Review review = new Review(
                        rs.getString("users.username"),
                        rs.getString("text"),
                        rs.getString("postDate"),
                        rs.getInt("rating"),
                        rs.getLong("reviews.id"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return reviews;
    }
}
