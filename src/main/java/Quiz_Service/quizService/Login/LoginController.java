package Quiz_Service.quizService.Login;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
   private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/signup/student")
    void signUpStudent(@RequestParam String username){
        loginService.addUser(username,false);
    }
    @PostMapping("signup/teacher")
    void signUpTeacher(@RequestParam String username){
        loginService.addUser(username,true);
    }
    @PostMapping("/login")
    void login(@RequestParam String username){
        loginService.checkAuth(username);
    }
    @GetMapping("/users")
    ResponseEntity<?> getAllUsers(){
        return loginService.retrieveAllUsers();
    }
    @GetMapping("/marks")
    ResponseEntity<?> getMarks(@RequestParam String username){
        return loginService.getMarks(username);
    }
    @GetMapping("/logout")
    void logout(){
        loginService.logout();
    }
}
