package com.tstu.repositoryJDBC.Test;

import com.tstu.model.FilmType;
import com.tstu.model.Role;
import com.tstu.model.User;
import com.tstu.repository.FilmRepositoryImpl;
import com.tstu.repository.UserRepository;
import com.tstu.repository.UserRepositoryImpl;

import java.sql.*;
import java.util.Map;

public class DataBaseConnection {
    public static void dB() {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet =null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:/C:/Users/Ulfsvartr/Desktop/JavaProjects/movielibrary/dataBase/movieLibraryDB", "root", "root");
            System.out.println("Соединение с СУБД выполнено!");
            //statement = connection.createStatement();
            preparedStatement =connection.prepareStatement( "select id, username, password from users");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.printf("%s - %s - %s", resultSet.getString("id"), resultSet.getString("username"),resultSet.getString("password"));
                System.out.println();
            }
            resultSet.close();
            preparedStatement.close();
            ///
            preparedStatement = connection.prepareStatement( "select imdbId, name, type, releaseDate from films");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.printf("%s - %s - %s - %s", resultSet.getString("imdbId"), resultSet.getString("name"),resultSet.getString("type"),resultSet.getString("releaseDate"));
                System.out.println();
            }
            resultSet.close();
            preparedStatement.close();
            ///
            preparedStatement = connection.prepareStatement( "select id, name from genres");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.printf("%s - %s", resultSet.getString("id"), resultSet.getString("name"));
                System.out.println();
            }
            resultSet.close();
            preparedStatement.close();
            ///
            preparedStatement = connection.prepareStatement( "select id, film_id,genre_id  from film_generes");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.printf("%s - %s - %s", resultSet.getString("id"), resultSet.getString("film_id"), resultSet.getString("genre_id"));
                System.out.println();
            }
            resultSet.close();
            preparedStatement.close();
            /* int count = statement.executeUpdate("insert into users(username,password) values('Ermak','Ermak')");
             count = statement.executeUpdate("insert into users(username,password) values('BigBoss','Big')");
             count = statement.executeUpdate("insert into users(username,password) values('Sasha452','Sasha')");
             count = statement.executeUpdate("insert into users(username,password) values('Den','den')");
             count = statement.executeUpdate("insert into users(username,password) values('Fox','foxtrot')");*/

/*             int count = statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0093058','Цельнометаллическая оболочка','Фильм','1987-07-10')");
             count = statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0110912','Криминальное чтиво','Фильм','1994-09-23')");
             count = statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0105236','Бешеные псы','Фильм','1992-01-21')");
             count = statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt0944947','Игра престолов','Сериал','2011-04-17')");
             count = statement.executeUpdate("insert into films(imdbId,name,type,releaseDate) values('tt2388725','Бумажный роман','Короткометражный фильм','2012-11-02')");

             count = statement.executeUpdate("insert into genres(name) values('Криминал')");
             count = statement.executeUpdate("insert into genres(name) values('Драма')");
             count = statement.executeUpdate("insert into genres(name) values('Триллер')");
             count = statement.executeUpdate("insert into genres(name) values('Приключенческий')");
             count = statement.executeUpdate("insert into genres(name) values('Комедия')");
             count = statement.executeUpdate("insert into genres(name) values('Боевик')");
             count = statement.executeUpdate("insert into genres(name) values('Документальный')");
             count = statement.executeUpdate("insert into genres(name) values('Анимация')");*/
             //int count = statement.executeUpdate("insert into genres(name) values('Экшн')");

/*int count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0093058','2')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0093058','7')");

             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0110912','1')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0110912','2')");

             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0105236','1')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0105236','2')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0105236','3')");

             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0944947','4')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0944947','5')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt0944947','2')");

             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt2388725','6')");
             count = statement.executeUpdate("insert into film_generes(film_id,genre_id) values('tt2388725','8')");*/

/*
    1CRIME("Криминал"),
    2DRAMA("Драма"),
    3THRILLER("Триллер"),
    4ACTION("Экшн"),
    5ADVENTURE("Приключенческий"),
    6COMEDY("Комедия"),
    7WAR("Боевик"),
    8DOCUMENTARY("Документальный"),
    9ANIMATION("Анимация");

Цельнометаллическая оболочка;tt0093058;Фильм;Драма,Боевик;10.07.1987
Криминальное чтиво;tt0110912;Фильм;Криминал,Драма;23.09.1994
Бешеные псы;tt0105236;Фильм;Криминал,Драма,Триллер;21.01.1992
Игра престолов;tt0944947;Сериал;Экшн,Приключенческий,Драма;17.04.2011
Бумажный роман;tt2388725;Короткометражный фильм;Комедия,Анимация;02.11.2012


            statement.execute("create table users ( " +
                   "id number(10) primary key AUTO_INCREMENT,"+
                    "username varchar2(20),"+
                    "password varchar2(20))");

            statement.execute("create table films ( " +
                    "imdbId varchar2(50) primary key,"+
                    "name varchar2(100),"+
                    "type varchar2(50)," +
                    "releaseDate date)");

            statement.execute("create table genres ( " +
                    "id number(10) primary key AUTO_INCREMENT,"+
                    "name varchar2(100))");
*/

/*
            statement.execute("create table film_generes ( " +
                    "id number(10) primary key AUTO_INCREMENT,"+
                    "film_id varchar2(50)," +
                    "genre_id number(10)," +
                    "FOREIGN KEY (genre_id)  REFERENCES genres (id)," +
                    "FOREIGN KEY (film_id)  REFERENCES films (imdbId))");
*/

/*            statement.execute("create table reviews ( " +
                    "id number(10) primary key AUTO_INCREMENT,"+
                    "author number(10),"+
                    "text varchar2(500)," +
                    "rating number(1)," +
                    "postDate date," +
                    "film_id varchar2(50)," +
                    "FOREIGN KEY (author)  REFERENCES users (id)," +
                    "FOREIGN KEY (film_id)  REFERENCES films (imdbId))");*/
            //int deletedCount = stmt.executeUpdate("delete from book where id = 1");
            /*int updatedCount = statement.executeUpdate("update film_generes set genre_id = 6 where id ='2'");
            updatedCount = statement.executeUpdate("update film_generes set genre_id = 9 where id ='9'");
            updatedCount = statement.executeUpdate("update film_generes set genre_id = 5 where id ='11'");*/
            //statement = connection.prepareStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
/*             try {
               statement.close();
           } catch (SQLException e) {
                e.printStackTrace();
            }*/
            System.out.println("Отключение с СУБД выполнено!");
        }
    }
}
