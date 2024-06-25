package Quiz_Service.quizService.Login;
import Quiz_Service.quizService.DTO.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Repo Class implements CRUDRepository because no Paging or Sorting functionality used thus far.
 */
@Repository
public interface LoginRepo extends CrudRepository<User,String> {


    /**
     * Custom Query to Retrieve scores of specified username.
     */
    @Query("SELECT s.score FROM users u JOIN score s ON u.username = s.username where u.username= :username")
    List<Integer> retrieveScores(String username);
}
