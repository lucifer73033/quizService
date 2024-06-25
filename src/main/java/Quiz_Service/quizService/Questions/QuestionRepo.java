package Quiz_Service.quizService.Questions;
import Quiz_Service.quizService.DTO.Question;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to handle Question Management on DB side
 */
@Repository
public interface QuestionRepo extends CrudRepository<Question,Integer> {


    /**
     * Custom query to find all questions based on their difficulty
     */
    @Query("SELECT * FROM questions q WHERE q.difficulty=:difficulty")
    Iterable<Question> findQuestionsByDifficulty(String difficulty);


    /**
     * Custom query to get total number of questions
     */
    @Query("SELECT COUNT(*) FROM questions")
    int findNumberOfQuestions();


    /**
     * Custom query to insert score of a quiz to score table which has associated username of the current user(foreign key)
     */
    @Modifying
    @Query("INSERT into score values(:username,:score)")
    void addScore(String username,int score);
}
