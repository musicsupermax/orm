package processing;

import annotations.Column;
import annotations.Id;
import annotations.Table;

@Table(name = "user")
public class UserDemo {

    @Id
    @Column(value = "id",type = "int")
    private int id;

    @Column(value = "login", type = "varchar(50)")
    private String login;

    private int age;
}
