package processing;

import annotations.Column;
import annotations.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TableModification {

    private static String tableName;

    public void createTable() {
        String createTable = "CREATE TABLE " + tableName + " (";

    }

    public void dropTable() {

    }

    public void insertIntoTable() {

    }

    public void updateTable() {

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
        tableName = "";
        if (object != null) {
            Class<?> cl = object.getClass();
            if (cl.isAnnotationPresent(Table.class)) {
                tableName = cl.getAnnotation(Table.class).name();
            }
        } else {
            throw new NullPointerException();
        }
        return tableName.trim();
    }

    public static String getColumnsNames(Object object) throws NoSuchFieldException {
        StringBuilder columnName = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] field = cl.getDeclaredFields();
            for (Field field1 : field) {
                if (field1.isAnnotationPresent(Column.class)) {
                    Column column = field1.getAnnotation(Column.class);
                    columnName.append(column.value() + " ");
                }
            }
        } else {
            throw new NullPointerException();
        }
        return new String(columnName).trim();
    }

    public static String getColumnsTypes(Object object) {
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
    }

    public static void main(String[] args) throws NoSuchFieldException {
        /*try {
            findEntity("src\\main\\java\\processing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        System.out.println(getTableName(new User()));
        System.out.println(getColumnsNames(new User()));
        System.out.println(getColumnsTypes(new User()));
    }
}
