package gg.together.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private Integer id;

    private String name;
    private String password;
    private String nickname;
}
