import database.DatabaseManager;
import entities.User;
import processing.TableManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        DatabaseManager.createDatabase("demodata");
        TableManager.createTable(new User());
        TableManager.insertIntoTable(new User("Hello", 28));
        TableManager.select(new User());
    }
}
