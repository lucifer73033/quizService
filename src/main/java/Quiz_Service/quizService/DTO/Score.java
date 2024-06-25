package Quiz_Service.quizService.DTO;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


/**
 * DTO Class for Data Entry in DataBase. Username corresponds(foreign key) to the username of the
 * student whose score is to be stored.
 */
@Table("score")
public class Score {
    @Column("username")
    String username;
    @Column("score")
    int score;
}
