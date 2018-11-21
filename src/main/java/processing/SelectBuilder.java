package processing;

public class SelectBuilder {
    private static String select = "";
    private static String from = "";
    private static String where = "";
    private static String orderBy = "";

    public SelectBuilder select(String select) {
        SelectBuilder.select = "SELECT " + select + " ";
        return this;
    }

    public SelectBuilder from(String from) {
        SelectBuilder.from = "FROM " + from +  " ";
        return this;
    }

    public SelectBuilder where(String where) {
        SelectBuilder.where = "WHERE " + where + " ";
        return this;
    }

    public SelectBuilder orderBy(String orderBy) {
        SelectBuilder.orderBy = "ORDER BY " + orderBy + " ";
        return this;
    }

    public static String getSelectResult (){
        return select + from + where + orderBy;
    }
}
