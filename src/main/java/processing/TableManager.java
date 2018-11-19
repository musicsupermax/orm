package processing;

import annotations.Column;
import annotations.Table;
import database.DatabaseManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    public static void createTable(Object object) {
        String[] namesAndTypes = getColumnsNamesAndTypes(object);
        String tableName = getTableName(object);
        StringBuilder createTable = new StringBuilder();
        createTable.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        for (String element : namesAndTypes) {
            createTable
                    .append(element)
                    .append(",");
        }
        createTable.deleteCharAt(createTable.lastIndexOf(","));
        createTable.append(");");
        updateExecutor(new String(createTable));
    }

    public static void dropTable(String tableName) {
        String dropQuery = "DROP TABLE " + tableName;
        updateExecutor(dropQuery);
    }

    public static void insertIntoTable(Object object) throws IllegalAccessException {
        String insertQuery = "INSERT INTO " + getTableName(object) + " ("
                + getColumnsNames(object) + ") VALUES ("
                + getFieldsValues(object) + ")";
        updateExecutor(insertQuery);
    }

    public static void findEntities(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            if (fileNames != null) {
                for (String name : fileNames) {
                    File newFile = new File(path + File.separator + name);
                    if (newFile.isFile()) {
                        System.out.println("File " + newFile.getPath());
                    }
                }
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public static String getTableName(Object object) {
        String name = "";
        if (object != null) {
            Class<?> cl = object.getClass();
            if (cl.isAnnotationPresent(Table.class)) {
                name = cl.getAnnotation(Table.class).name();
            }
        } else {
            throw new NullPointerException();
        }
        return name.trim();
    }

    public static String[] getColumnsNamesAndTypes(Object object) {
        StringBuilder nameAndType = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] field = cl.getDeclaredFields();
            for (Field field1 : field) {
                if (field1.isAnnotationPresent(Column.class)) {
                    Column column = field1.getAnnotation(Column.class);
                    nameAndType
                            .append(column.value())
                            .append(" ")
                            .append(column.type())
                            .append(",");
                }
            }
        } else {
            throw new NullPointerException();
        }
        return new String(nameAndType).split(",");
    }

    public static String getFieldsValues(Object object) throws IllegalAccessException {
        StringBuilder values = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(object) instanceof String) {
                    values.append("\'")
                            .append(field.get(object).toString())
                            .append("\'")
                            .append(",");
                } else {
                    values.append(field.get(object).toString()).append(",");
                }
            }
            values.deleteCharAt(values.lastIndexOf(","));
        } else {
            throw new NullPointerException();
        }
        return new String(values);
    }

    public static String getColumnsNames(Object object) {
        StringBuilder names = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] field = cl.getDeclaredFields();
            for (Field field1 : field) {
                if (field1.isAnnotationPresent(Column.class)) {
                    Column column = field1.getAnnotation(Column.class);
                    names
                            .append(column.value())
                            .append(",");
                }
            }
        } else {
            throw new NullPointerException();
        }
        return new String(names.deleteCharAt(names.lastIndexOf(",")));
    }

    private static void updateExecutor(String query) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
