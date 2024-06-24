package Quiz_Service.quizService.Utilities.ExceptionHandlers;

import Quiz_Service.quizService.Utilities.Exceptions.AuthorisationIssueException;
import Quiz_Service.quizService.Utilities.Exceptions.NoUserException;
import Quiz_Service.quizService.Utilities.Exceptions.UserAlreadyExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerHandler {
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<?> noSuchElementExceptionHandler(){
        return ResponseEntity.status(500).body("No such User");
    }
    @ExceptionHandler(UserAlreadyExists.class)
    ResponseEntity<?> userAlreadyExistsHandler(){
        return ResponseEntity.status(500).body("User Already Exists");
    }
    @ExceptionHandler(AuthorisationIssueException.class)
    ResponseEntity<?> authorisationIssueExceptionHandler(){
        return ResponseEntity.status(500).body("Students arent allowed to alter the database you fucking twat");
    }
    @ExceptionHandler(NoUserException.class)
    ResponseEntity<?> noUserExceptionHandler(){
        return ResponseEntity.status(500).body("Login first cheeky boy");
    }

}
