import database.DatabaseManager;
import entities.User;
import processing.SelectBuilder;
import processing.TableManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        DatabaseManager.createDatabase("demodata");
        TableManager.createTable(new User());
        TableManager.insertIntoTable(new User("Why", 28));
        TableManager.insertIntoTable(new User("Hello", 15));
        TableManager.insertIntoTable(new User("Wasserwage", 3));
        TableManager.insertIntoTable(new User("Good", 75));
        TableManager.insertIntoTable(new User("Tesla", 118));
        TableManager.insertIntoTable(new User("Perry", 84));
        new SelectBuilder().select("*").from("users").where("id > 2").orderBy("age");
        TableManager.select();
    }
}
