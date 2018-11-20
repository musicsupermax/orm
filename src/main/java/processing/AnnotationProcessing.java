package processing;

import annotations.Column;
import annotations.Id;
import annotations.Select;
import annotations.Table;

import java.lang.reflect.Field;

public class AnnotationProcessing {

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

    public static String[] getColumns(Object object) {
        StringBuilder columnsBuilder = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    columnsBuilder
                            .append(column.name())
                            .append(" ")
                            .append(column.type());
                    if (column.isAutoIncrement() && field.isAnnotationPresent(Id.class)) {
                        columnsBuilder.append(" AUTO_INCREMENT");
                    }
                    if (!column.isNull()) {
                        columnsBuilder.append(" NOT NULL");
                    }
                    columnsBuilder.append(",");
                }
            }
        } else {
            throw new NullPointerException();
        }
        return new String(columnsBuilder).split(",");
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
                    names.append(column.name()).append(",");
                }
            }
        } else {
            throw new NullPointerException();
        }
        return new String(names.deleteCharAt(names.lastIndexOf(",")));
    }

    public static String getPrimaryKey(Object object) {
        StringBuilder primaryKey = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] fields = cl.getDeclaredFields();
            primaryKey.append("PRIMARY KEY (");
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    primaryKey
                            .append(column.name())
                            .append(",");
                }
            }
            primaryKey.deleteCharAt(primaryKey.lastIndexOf(",")).append(")");
        } else {
            throw new NullPointerException();
        }
        return new String(primaryKey);
    }

    public static String getSelectValues(Object object){
        StringBuilder selectBuilder = new StringBuilder();
        if (object != null) {
            Class<?> cl = object.getClass();
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Select.class) && field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    selectBuilder
                            .append(column.name())
                            .append(",");
                }
            }
            selectBuilder.deleteCharAt(selectBuilder.lastIndexOf(","));
        } else {
            throw new NullPointerException();
        }
        return new String(selectBuilder);
    }
}
