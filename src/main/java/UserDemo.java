import annotations.Column;
import annotations.Id;
import annotations.Table;

@Table(name = "users")
public class UserDemo {

    @Id
    @Column(value = "id",type = "int")
    private int id;

    @Column(value = "login", type = "varchar(50)")
    private String login;

    @Column(value = "age", type = "int")
    private int age;

    public UserDemo() {
    }

    public UserDemo(int id, String login, int age) {
        this.id = id;
        this.login = login;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
