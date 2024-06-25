package Quiz_Service.quizService.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle all User Related HTTP Requests
 */
@RestController
public class LoginController {
   private final LoginService loginService;//provides all the services and also maintains the current logged in user
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Http request for Students to sign up for service. False auth signifies doesnt have authorisation to make changes
     * question database
     * @param username of the signing up user(Password management not yet implemented)
     * @return User Already Exists ResponseEntity if user already exist
     */
    @PostMapping("/signup/student")
    void signUpStudent(@RequestParam String username){
        loginService.addUser(username,false);
    }


    /**
     * Http request for Teachers to sign up for service. True auth signifies, user has authorisation to make changes to
     * question database
     * @param username of the signing up user(Password management not yet implemented)
     * @return User Already Exists ResponseEntity if user already exist
     */
    @PostMapping("signup/teacher")
    void signUpTeacher(@RequestParam String username){
        loginService.addUser(username,true);
    }


    /**
     * HTTP request to login.
     * @param username (Password functionality not yet implemented)
     * @return User Doesnt Exist ResponseEntity if user doesnt exist
     */
    @PostMapping("/login")
    void login(@RequestParam String username){
        loginService.checkAuth(username);
    }


    /**
     * Http request to get all users in the database. Can only be done if the current logged in user is a Teacher.
     * @param
     * @return All users currently in the database.
     */
    @GetMapping("/users")
    ResponseEntity<?> getAllUsers(){
        return loginService.retrieveAllUsers();
    }


    /**
     * Http request to get all previous marks of the current logged in user
     * @param
     * @return previous marks of current logged in user
     */
    @GetMapping("/marks")
    ResponseEntity<?> getMarks(){
        return ResponseEntity.status(200).body(loginService.retrieveMarks());
    }


    /**
     * Http request to logout
     */
    @GetMapping("/logout")
    void logout(){
        loginService.logout();
    }
}
