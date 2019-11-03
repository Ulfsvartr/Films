package com.tstu.repositoryJDBC;

import java.sql.*;

public class DataBaseConnection {
    public static void dB() {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:/C:/Users/Ulfsvartr/Desktop/JavaProjects/movielibrary/dataBase/movieLibraryDB", "root", "root");
            System.out.println("Соединение с СУБД выполнено!");

            statement = connection.createStatement();
            //int count = stmt.executeUpdate("insert into book(id,author,name,year) values(1, 'Толстой Л.Н.','Война и мир',1865)");
            //count = stmt.executeUpdate("insert into book(id,author,name,year) values(2,'Булгаков М.А.','Мастер и Маргарита', 1966)");

            //statement.execute("create table users ( " +
            //        "id number(10) primary key AUTO_INCREMENT,"+
            //        "username varchar2(20),"+
             //       "password varchar2(20))");

            //statement.execute("create table films ( " +
            //        "imdbId varchar2(50) primary key,"+
             //       "name varchar2(100),"+
            //        "type varchar2(50)," +
            //        "genre varchar2(250),"+
            //        "releaseDate date)");

            //statement.execute("create table reviews ( " +
            //        "id number(10) primary key AUTO_INCREMENT,"+
            //        "author number(10),"+
            //        "text varchar2(500)," +
            //        "rating number(1)," +
            //        "postDate date," +
            //        "filmID varchar2(50)," +
            //        "FOREIGN KEY (author)  REFERENCES users (id)," +
            //        "FOREIGN KEY (filmID)  REFERENCES films (imdbId))");
            //int deletedCount = stmt.executeUpdate("delete from book where id = 1");
            //int updatedCount = stmt.executeUpdate("update book set year = 2015 where author ='Лукъяненко'");
            //statement = connection.prepareStatement();
            connection.close();
            statement.close();
            System.out.println("Отключение с СУБД выполнено!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
           } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
