package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public Connection connect() {
        String url = "jdbc:mysql://localhost:3306/user?serverTimezone=UTC";
        String username = "root";
        String password = "Zebra_///123";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
