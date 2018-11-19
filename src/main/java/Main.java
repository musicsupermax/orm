import database.DatabaseManager;
import processing.TableManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        DatabaseManager.createDatabase("demodata");
        TableManager.createTable(new UserDemo());
        TableManager.insertIntoTable(new UserDemo(5,"Hello", 28));
        TableManager.dropTable("users");
    }
}
