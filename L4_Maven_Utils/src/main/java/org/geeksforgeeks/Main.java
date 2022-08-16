package org.geeksforgeeks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        int a = 10;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy_db",
                "root", "password");

        Statement statement = connection.createStatement();
        statement.execute("create table jbdl_36(id int, name varchar(30))");
    }
}