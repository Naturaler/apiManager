package com.yrx.datasourcemanager.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?serverTimezone=UTC", "root", "root");
        Statement statement = connection.createStatement();
        boolean show_databases_ = statement.execute("show databases ");
        System.out.println(show_databases_);
    }

}
