package Quiz_Service.quizService.DTO;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("score")
public class Score {
    @Column("username")
    String username;
    @Column("score")
    int score;
}
