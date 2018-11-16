import processing.TableModification;
import processing.User;

public class Main {
        public static void main(String[] args) throws NoSuchFieldException {
        /*try {
            findEntity("src\\main\\java\\processing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
            System.out.println(TableModification.getTableName(new User()));
            System.out.println(TableModification.getColumnsNames(new User()));
            System.out.println(TableModification.getColumnsTypes(new User()));
    }
}
