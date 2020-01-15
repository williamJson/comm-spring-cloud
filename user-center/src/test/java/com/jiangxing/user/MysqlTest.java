package com.jiangxing.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@RunWith(SpringRunner.class)
public class MysqlTest {


    @Autowired
    private DataSource dataSource;

    @Test
    public void connectMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String user = "root";
        String password = null;
        String url = "jdbc:mysql://192.168.124.124:3306/test?useUnicode=true&characterEncoding=utf-8";
        Properties info = new Properties();
        info.put("user", "java");
        info.put("password", "java123");
        try {
            Connection connection = DriverManager.getConnection(url, info);
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
