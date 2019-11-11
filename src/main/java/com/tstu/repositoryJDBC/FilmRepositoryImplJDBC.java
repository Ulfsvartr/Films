package com.tstu.repositoryJDBC;

import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.repository.FilmRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmRepositoryImplJDBC implements FilmRepository {

    private static FilmRepository instance;

    private FilmRepositoryImplJDBC() {
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepositoryImplJDBC();
        }
        return instance;
    }

    @Override
    public List<Film> findFilmList(String name,String imdbId,String type, String genre,String releaseDate) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FilmJDBC filmJDBC;
        List <Film> films= new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:/C:/Users/Ulfsvartr/Desktop/JavaProjects/movielibrary/dataBase/movieLibraryDB", "root", "root");
            stmt = con.prepareStatement("SELECT films.*, GROUP_CONCAT(genres.name) AS genre FROM films " +
                    "INNER JOIN film_generes ON films.imdbId = film_generes.film_id "+
                    "LEFT JOIN genres ON film_generes.genre_id = genres.id " +
                    "WHERE films.name LIKE ? AND imdbId LIKE ? AND type LIKE ? AND releaseDate LIKE ? "+
                    "GROUP BY films.imdbId ");
            stmt.setString(1, "%"+name+"%");
            stmt.setString(2, "%"+imdbId+"%");
            stmt.setString(3, "%"+type+"%");
            stmt.setString(4, "%"+releaseDate+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                filmJDBC = new FilmJDBC(rs.getString("imdbId"),rs.getString("type"), rs.getString("name"),  rs.getString("genre"), rs.getString("releaseDate"));
                films.add(ConvertToFilmJDBC.convertToFilm(filmJDBC));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception e) {
            }
        }
        return films;
    }

   /* private List<Review> getFilmReview(String imdbId){
        List<Review> reviews;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        *//*            statement.execute("create table reviews ( " +
                    "id number(10) primary key AUTO_INCREMENT,"+
                    "author number(10),"+
                    "text varchar2(500)," +
                    "rating number(1)," +
                    "postDate date," +
                    "film_id varchar2(50)," +
                    "FOREIGN KEY (author)  REFERENCES users (id)," +
                    "FOREIGN KEY (film_id)  REFERENCES films (imdbId))");*//*
        try {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection("jdbc:h2:/C:/Users/Ulfsvartr/Desktop/JavaProjects/movielibrary/dataBase/movieLibraryDB", "root", "root");
            stmt = con.prepareStatement("SELECT author,text,rating,postDate FROM reviews " +
                    "INNER JOIN users ON reviews.imdbId = users.id "+
                    "WHERE film_id = ? "+
                    "GROUP BY films.imdbId ");
            stmt.setString(1, "%"+imdbId+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Review = new Review(rs.getString("imdbId"),rs.getString("type"), rs.getString("name"),  rs.getString("genre"), rs.getString("releaseDate"));
                reviews.add();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception e) {
            }
        }
        return null;
    }*/

    @Override
    public Review saveReview(Film film, User user, String text, int rating) {
        Connection con = null;
        Statement stmt = null;
        Review review = new Review(user,text,rating);
        try {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection("jdbc:h2:/C:/Users/Ulfsvartr/Desktop/JavaProjects/movielibrary/dataBase/movieLibraryDB", "root", "root");
            stmt = con.createStatement();
            int count = stmt.executeUpdate("insert into reviews(author,text,rating,postDate,film_id) values('"+user.getId()+"','"+review.getText()+"','"+review.getRating()+"','"+review.getData()+"','"+film.getImdbId()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception e) {
            }
        }
        return review;
    }


}
