import processing.TableModification;
import processing.UserDemo;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        /*try {
            findEntity("src\\main\\java\\processing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        TableModification.createTable(new UserDemo());
       /* System.out.println(TableModification.getTableName(new UserDemo()));
        System.out.println(TableModification.getColumnsNamesAndTypes(new UserDemo()));*/
    }
}
