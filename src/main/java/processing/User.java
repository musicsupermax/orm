package processing;

import annotations.Column;
import annotations.Id;
import annotations.Table;

@Table(name = "user")
public class User {

    @Id
    @Column(value = "id",type = "int")
    private int id;

    @Column(value = "password", type = "varchar")
    private String password;

    private int age;
}
