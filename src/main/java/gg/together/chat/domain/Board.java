package gg.together.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {
    
    @Id
    private Integer id;

    private String title;
    private String content;
    private String update_time;
    private Integer view;
}
