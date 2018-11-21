package entities;

import annotations.Column;
import annotations.Id;
import annotations.Table;

@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", type = "int", isAutoIncrement = true)
    private int id;

    @Column(name = "login", type = "varchar(50)")
    private String login;

    @Column(name = "age", type = "int")
    private int age;

    public User() {
    }

    public User(int id, String login, int age) {
        this.id = id;
        this.login = login;
        this.age = age;
    }

    public User(String login, int age) {
        this.login = login;
        this.age = age;
    }

    public User(int id) {
        this.id = id;
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
