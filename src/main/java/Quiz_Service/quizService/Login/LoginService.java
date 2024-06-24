package Quiz_Service.quizService.Login;

import Quiz_Service.quizService.DTO.User;
import Quiz_Service.quizService.Utilities.Exceptions.UserAlreadyExists;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LoginService {
    private final LoginRepo loginRepo;
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LoginService(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }
    boolean checkAuth(String username){
        User user=loginRepo.findById(username).get();
        this.setUser(user);
        return true;
    }
    void addUser(String username,boolean auth){
        if (!loginRepo.existsById(username)) {

            loginRepo.save(new User(username,auth));
        } else {
            throw new UserAlreadyExists();
        }
    }
    Iterable<Integer> retrieveMarks(){
        return loginRepo.retrieveScores(this.getUser().getUsername());
    }
    ResponseEntity<?> retrieveAllUsers(){
        return ResponseEntity.status(500).body(loginRepo.findAll());
    }
    ResponseEntity<?> getMarks(String username){
        return ResponseEntity.status(200).body(loginRepo.retrieveScores(username));
    }
    void logout(){
        user=null;
    }
}
