package processing;

import database.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static processing.AnnotationProcessing.*;

public class TableManager {

    public static void createTable(Object object) {
        String[] columns = getColumns(object);
        String tableName = getTableName(object);
        StringBuilder createTable = new StringBuilder();
        createTable.append("CREATE TABLE IF NOT EXISTS ")
                .append(tableName).append(" (");
        for (String element : columns) {
            createTable
                    .append(element)
                    .append(",");
        }
        createTable.append(getPrimaryKey(object)).append(");");
        updateQueryExecutor(new String(createTable));
    }

    public static void dropTable(String tableName) {
        String dropQuery = "DROP TABLE " + tableName;
        updateQueryExecutor(dropQuery);
    }

    public static void insertIntoTable(Object object) throws IllegalAccessException {
        String insertQuery = "INSERT INTO " + getTableName(object) + " ("
                + getColumnsNames(object) + ") VALUES ("
                + getFieldsValues(object) + ")";
        updateQueryExecutor(insertQuery);
    }

    public static void select(Object object) throws SQLException {
        String tableName = getTableName(object);
        String selectQuery = "SELECT " + getSelectValues(object) + " FROM " + tableName;
        queryExecutor(selectQuery);
        /*ResultSet resultSet = queryExecutor(selectQuery);
        for (String element: getColumnsNames(object).split(",")){

        }
       *//* System.out.printf("%-20s%s%n", "id", "username");
        System.out.println("-------------------------------");*//*
       String colum = "";
        while (resultSet.next()) {
            System.out.println(resultSet.toString());
            *//*int id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            System.out.printf("%-20d%s%n", id, name);*//*
        }*/
    }

    public static void selectAll(Object object) {
        String tableName = getTableName(object);
        String selectQuery = "SELECT * " + "FROM " + tableName;
    }

    private static void updateQueryExecutor(String query) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void queryExecutor(String query) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
