package Quiz_Service.quizService.Utilities.ExceptionHandlers;
import Quiz_Service.quizService.Utilities.Exceptions.AuthorisationIssueException;
import Quiz_Service.quizService.Utilities.Exceptions.NoUserException;
import Quiz_Service.quizService.Utilities.Exceptions.UserAlreadyExists;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


/**
 * To handle various Exceptions that might occur when handling various HTTP requests in all Controllers
 */
@RestControllerAdvice
public class ControllerHandler {


    /**
     * To handle error when trying to login with a username that doesnt exist in DB
     */
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<?> noSuchElementExceptionHandler(){
        return ResponseEntity.status(500).body("No such User");
    }


    /**
     * To handle duplication when trying to sign up with a username that already exists in DB
     */
    @ExceptionHandler(UserAlreadyExists.class)
    ResponseEntity<?> userAlreadyExistsHandler(){
        return ResponseEntity.status(500).body("User Already Exists");
    }


    /**
     * To handle Authorisation Level Prob. Only Teachers can augment the questions table of DB
     */
    @ExceptionHandler(AuthorisationIssueException.class)
    ResponseEntity<?> authorisationIssueExceptionHandler(){
        return ResponseEntity.status(500).body("Students arent allowed to alter the database you fucking twat");
    }


    /**
     * To handle no logged in user
     */
    @ExceptionHandler(NoUserException.class)
    ResponseEntity<?> noUserExceptionHandler(){
        return ResponseEntity.status(500).body("Login first cheeky boy");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("bababoeyy");
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message= null;
        if (fieldError != null) {
            message = fieldError.getDefaultMessage();
        }
        return ResponseEntity.status(500).body(message);

    }

}
