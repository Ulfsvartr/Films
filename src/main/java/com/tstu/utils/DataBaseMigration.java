package com.tstu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMigration {
    private static Connection connection;

    public static void deleteAllTables() {

    }


    public static void createTables() {
        Statement statement = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            statement = connection.createStatement();

            statement.execute("create table if not exists users ( " +
                    "id number(10) primary key AUTO_INCREMENT," +
                    "username varchar2(20)," +
                    "password varchar2(20))");

            statement.execute("create table if not exists films ( " +
                    "imdbId varchar2(50) primary key," +
                    "name varchar2(100)," +
                    "type varchar2(50)," +
                    "releaseDate date)");

            statement.execute("create table if not exists genres ( " +
                    "id number(10) primary key AUTO_INCREMENT," +
                    "name varchar2(100))");


            statement.execute("create table if not exists  film_generes ( " +
                    "id number(10) primary key AUTO_INCREMENT," +
                    "film_id varchar2(50)," +
                    "genre_id number(10)," +
                    "FOREIGN KEY (genre_id)  REFERENCES genres (id)," +
                    "FOREIGN KEY (film_id)  REFERENCES films (imdbId))");


            statement.execute("create table  if not exists reviews ( " +
                    "id number(10) primary key AUTO_INCREMENT," +
                    "author number(10)," +
                    "text varchar2(500)," +
                    "rating number(2)," +
                    "postDate date," +
                    "film_id varchar2(50)," +
                    "FOREIGN KEY (author)  REFERENCES users (id)," +
                    "FOREIGN KEY (film_id)  REFERENCES films (imdbId))");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
        }
    }


    public static void fillTables() {
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DbConstants.url, DbConstants.user, DbConstants.password);
            statement = connection.createStatement();
            statement.executeUpdate("insert into users(username,password) values('Ermak','Ermak')");
            statement.executeUpdate("insert into users(username,password) values('BigBoss','Big')");
            statement.executeUpdate("insert into users(username,password) values('Sasha452','Sasha')");
            statement.executeUpdate("insert into users(username,password) values('Den','den')");
            statement.executeUpdate("insert into users(username,password) values('Fox','foxtrot')");

            statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0093058','Цельнометаллическая оболочка','Фильм','1987-07-10')");
            statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0110912','Криминальное чтиво','Фильм','1994-09-23')");
            statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0105236','Бешеные псы','Фильм','1992-01-21')");
            statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0944947','Игра престолов','Сериал','2011-04-17')");
            statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt2388725','Бумажный роман','Короткометражный фильм','2012-11-02')");

            statement.executeUpdate("insert into genres(name) values('Криминал')");
            statement.executeUpdate("insert into genres(name) values('Драма')");
            statement.executeUpdate("insert into genres(name) values('Триллер')");
            statement.executeUpdate("insert into genres(name) values('Приключенческий')");
            statement.executeUpdate("insert into genres(name) values('Комедия')");
            statement.executeUpdate("insert into genres(name) values('Боевик')");
            statement.executeUpdate("insert into genres(name) values('Документальный')");
            statement.executeUpdate("insert into genres(name) values('Анимация')");
            statement.executeUpdate("insert into genres(name) values('Экшн')");

            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0093058','2')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0093058','7')");

            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0110912','1')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0110912','2')");

            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0105236','1')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0105236','2')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0105236','3')");

            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0944947','4')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0944947','5')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0944947','2')");

            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt2388725','6')");
            statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt2388725','8')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
        }
    }
}
