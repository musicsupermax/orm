package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection("zxc");
    }
}
