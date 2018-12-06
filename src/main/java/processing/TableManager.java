package processing;

import database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        executeUpdate(new String(createTable));
    }

    public static void dropTable(String tableName) {
        String dropQuery = "DROP TABLE " + tableName;
        executeUpdate(dropQuery);
    }

    public static void insertIntoTable(Object object) throws IllegalAccessException {
        String insertQuery = "INSERT INTO " + getTableName(object) + " ("
                + getColumnsNames(object) + ") VALUES ("
                + getFieldsValues(object) + ")";
        executeUpdate(insertQuery);
    }

    public static void select() throws SQLException {
        String selectQuery = SelectBuilder.getSelectResult();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                columnNames.add(resultSetMetaData.getColumnLabel(i));
            }
            while (resultSet.next()) {
                List<Object> rowData = new ArrayList<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    rowData.add(resultSet.getObject(i));
                }
                System.out.println();
                for (int colIndex = 0; colIndex < resultSetMetaData.getColumnCount(); colIndex++) {
                    String objString = "";
                    Object columnObject = rowData.get(colIndex);
                    if (columnObject != null) {
                        objString = columnObject.toString() + " ";
                    }
                    System.out.printf("  %s: %s%n",
                            columnNames.get(colIndex), objString);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute update
     * @param query
     */
    private static void executeUpdate(String query) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
