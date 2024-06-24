package Quiz_Service.quizService.Utilities.Aspects;

import Quiz_Service.quizService.Utilities.Exceptions.AuthorisationIssueException;
import Quiz_Service.quizService.Utilities.Exceptions.NoUserException;
import Quiz_Service.quizService.Login.LoginService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspects {
   private LoginService loginService;

    public Aspects(LoginService loginService) {
        this.loginService = loginService;
    }

    @Around("@annotation(Quiz_Service.quizService.Utilities.Annotations.CheckUserStatus)")
    public void checkUserStatus(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        if (loginService.getUser()==null) throw new NoUserException();
        else if (!loginService.getUser().getAuth()) throw new AuthorisationIssueException();
        else proceedingJoinPoint.proceed();
    }
}
