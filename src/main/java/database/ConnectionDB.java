package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB = "catalog";
    public static final String URL = "jdbc:mysql://localhost:3306/" + DB;
    public static final String USER = "admin";
    public static final String PASSWORD = "vcxz+3210";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}