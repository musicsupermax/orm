package processing;

import annotations.Column;
import annotations.Table;
import database.ConnectionDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableModification {

    public static void createTable(Object object) {
        String[] namesAndTypes = getColumnsNamesAndTypes(object);
        String tableName = getTableName(object);
        StringBuilder createTable = new StringBuilder();
        createTable.append("CREATE TABLE ").append(tableName).append(" (");
        for (String element : namesAndTypes) {
            createTable
                    .append(element)
                    .append(",");
        }
        createTable.deleteCharAt(createTable.lastIndexOf(","));
        System.out.println(createTable.append(");"));
        try (Connection connection = ConnectionDB.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(new String(createTable));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findEntity(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            if (fileNames != null) {
                for (String name : fileNames) {
                    File newFile = new File(path + File.separator + name);
                    if (newFile.isDirectory()) {
                        System.out.println("Folder " + newFile.getAbsolutePath());
                    } else {
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

    /*public static String getColumnsTypes(Object object) {
        StringBuilder columnType = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] field = cl.getDeclaredFields();
            for (Field field1 : field) {
                if (field1.isAnnotationPresent(Column.class)) {
                    Column column = field1.getAnnotation(Column.class);
                    columnType.append(column.type() + " ");
                }
            }
        } else {
            throw new NullPointerException();
        }
        return new String(columnType).trim();
    }*/
}
