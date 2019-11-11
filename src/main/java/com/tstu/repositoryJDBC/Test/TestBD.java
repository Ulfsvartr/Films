package com.tstu.repositoryJDBC.Test;

import java.sql.*;

public class TestBD {
    public static void testBD() {
        PreparedStatement statement = null;
        //Statement stmt = null;
        Connection connection = null;
        ResultSet rs = null;
        //ResultSet resultSet = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:/C:/Users/Ulfsvartr/Desktop/JavaProjects/movielibrary/bd/testBD", "root", "root");
            System.out.println("Соединение с СУБД выполнено!");

            statement = connection.prepareStatement("select author, name, id, year  from book"); //where name like ?");
            //statement.setString(1, "%В%");
            rs = statement.executeQuery();
            while (rs.next()) {
                System.out.printf("%s - %s - %s - %s", rs.getString("author"), rs.getString("name"), rs.getString("id"), rs.getString("year"));
                System.out.println();
            }
            //stmt = connection.createStatement();
            //int count = stmt.executeUpdate("insert into book(id,author,name,year) values(1, 'Толстой Л.Н.','Война и мир',1865)");
            //count = stmt.executeUpdate("insert into book(id,author,name,year) values(2,'Булгаков М.А.','Мастер и Маргарита', 1966)");

            //stmt.execute("create table book ( " +
            //        "id number(10) primary key,"+
            //        "author varchar2(100),"+
            //        "name varchar2(250),"+
            //        "year number(4))");



            //int deletedCount = stmt.executeUpdate("delete from book where id = 1");
            //int updatedCount = stmt.executeUpdate("update book set year = 2015 where author ='Лукъяненко'");
            //statement = connection.prepareStatement();
            connection.close();
            statement.close();
            rs.close();
            System.out.println("Отключение с СУБД выполнено!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
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
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           /*try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }

    }

}
