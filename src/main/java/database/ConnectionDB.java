package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB = "catalog";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB;
    private static final String USER = "admin";
    private static final String PASSWORD = "vcxz+3210";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}