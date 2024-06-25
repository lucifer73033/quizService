package Quiz_Service.quizService.Login;
import Quiz_Service.quizService.DTO.User;
import Quiz_Service.quizService.Utilities.Annotations.CheckUserStatus;
import Quiz_Service.quizService.Utilities.Exceptions.UserAlreadyExists;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


/**
 * Service class to handle all the various data processing requirements from the controller
 */
@Service
@SessionScope
public class LoginService {
    private final LoginRepo loginRepo;//Repository Class for DataBase I/O
    private User user;//maintains the current logged in user
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LoginService(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }


    /**
     * Sets the current user to the username provided.If no such user found returns appropriate message(Handled with the
     * help of RestControllerAdvice Class). Password checking functionality WIP.
     */
    boolean checkAuth(String username){
        User user=loginRepo.findById(username).get();
        this.setUser(user);
        return true;
    }


    /**
     * Adds User to the DataBase along with their authorisation level. If user already exists, Returns appropriate message
     * (Handled by RestControllerAdvice class
     */
    void addUser(String username,boolean auth){
        if (!loginRepo.existsById(username)) {

            loginRepo.save(new User(username,auth));
        } else {
            throw new UserAlreadyExists();
        }
    }


    /**
     * Returns all the previous marks of current logged in user.
     */
    Iterable<Integer> retrieveMarks(){
        return loginRepo.retrieveScores(this.getUser().getUsername());
    }


    /**
     * Returns all the users in the DataBase. Can only be accessed by Teachers.
     */
    @CheckUserStatus
    ResponseEntity<?> retrieveAllUsers(){
        return ResponseEntity.status(500).body(loginRepo.findAll());
    }

    /**
     * Logs out current logged in user
     */
    void logout(){
        user=null;
    }
}
