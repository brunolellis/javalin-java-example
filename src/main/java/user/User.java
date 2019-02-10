package user;

import lombok.Value;

@Value
public class User {
    private Integer id;
    private String name;
    private String email;
}
