package Quiz_Service.quizService.Questions;

import Quiz_Service.quizService.DTO.Question;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends CrudRepository<Question,Integer> {
    @Query("SELECT * FROM questions q WHERE q.difficulty=:difficulty")
    Iterable<Question> findQuestionsByDifficulty(String difficulty);
    @Query("SELECT COUNT(*) FROM questions")
    int findNumberOfQuestions();
}
