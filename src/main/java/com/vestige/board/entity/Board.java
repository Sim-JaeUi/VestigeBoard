package com.vestige.board.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(
        name="seq_board",
        sequenceName = "seq_board",
        initialValue = 1,
        allocationSize = 1
)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_board")
    private Long idx;
    private String title;
    private String name;
    private String email;
    private String phone;
    private String content;
//    private String fileName;
//    private String filePath;
//    @CreatedDate
//    private LocalDateTime regDate;
}
