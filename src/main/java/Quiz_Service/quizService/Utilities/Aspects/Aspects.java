package Quiz_Service.quizService.Utilities.Aspects;
import Quiz_Service.quizService.Utilities.Exceptions.AuthorisationIssueException;
import Quiz_Service.quizService.Utilities.Exceptions.NoUserException;
import Quiz_Service.quizService.Login.LoginService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


//Aspect class to intercept methods based on Annotation
@Aspect
@Component
public class Aspects {
   private LoginService loginService;//to retrieve current logged in user

    public Aspects(LoginService loginService) {
        this.loginService = loginService;
    }


    /**
     * Intercepts methods annotated with @CheckUserStatus and runs an authorisation check and also if no user is logged in
     */
    @Around("@annotation(Quiz_Service.quizService.Utilities.Annotations.CheckUserStatus)")
    public void checkUserStatus(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        if (loginService.getUser()==null) throw new NoUserException();//if no logged in user
        else if (!loginService.getUser().getAuth()) throw new AuthorisationIssueException();//if current user is not a teacher
        else proceedingJoinPoint.proceed();
    }
}
