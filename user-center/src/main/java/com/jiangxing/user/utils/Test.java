package com.jiangxing.user.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String user = "root";
        String password = "";
        String url = "jdbc:mysql://192.168.124.124:3306/test?useUnicode=true&characterEncoding=utf-8";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                System.out.println(resultSet.getLong(1));
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
