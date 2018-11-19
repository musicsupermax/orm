package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DatabaseManager {
    private static String url;
    private static String dbName;
    private static String user;
    private static String password;
    private static final String CREATE_DATABASE_QUERY =
            "CREATE DATABASE IF NOT EXISTS ";

    public static Connection getConnection() throws SQLException {
        getProperties();
        return DriverManager.getConnection(url + dbName, user, password);
    }

    public static void createDatabase(String name) throws SQLException {
        getProperties();
        dbName = name;
        String urlForCreatingDB = url + "mysql";
        try (Connection connection =
                     DriverManager.getConnection(urlForCreatingDB, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_DATABASE_QUERY + name);
        }
    }

    private static void getProperties() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        url = resourceBundle.getString("url");
        user = resourceBundle.getString("user");
        password = resourceBundle.getString("password");
    }

    public static String getCurrentDatabase() {
        return dbName;
    }

    public static void setCurrentDatabase(String name) {
        dbName = name;
    }
}