package Quiz_Service.quizService.Questions;
import Quiz_Service.quizService.DTO.QuizAnswers;
import Quiz_Service.quizService.Login.LoginService;
import Quiz_Service.quizService.Utilities.Annotations.CheckUserStatus;
import Quiz_Service.quizService.DTO.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Service class to handle all the various data processing requirements from the controller
 */
@Service
public class QuestionService {
    private final QuestionRepo questionRepo;/* handles all DB Management */
    private final LoginService loginService;/* used to retrieve the current logged in user */
    public QuestionService(QuestionRepo questionRepo,LoginService loginService) {
        this.questionRepo = questionRepo;
        this.loginService=loginService;

    }


    /**
     * Adds question to the database. Only done by teachers
     * @CheckUserStatus tells Spring to intercept and run an advice that(find in Utilities\Aspects) checks the authorisation of current user.
     */
    @CheckUserStatus
    void addQuestion(Question question){
         questionRepo.save(question);
    }


    /**
     * Deletes a question of the ID id
     * @CheckUserStatus tells Spring to intercept and run an advice that(find in Utilities\Aspects) checks the authorisation of current user.
     */
    @CheckUserStatus
    void deleteById(Integer id){
        questionRepo.deleteById(id);
    }


    /**
     * Returns ResponseEntity containing random n number of questions based on difficulty
     * If n is more than total number of questions then returns all questions
     * Found_Status header contains the number of questions retrieved
     */
    ResponseEntity<?> findNQuestionsByDifficulty(int n,String difficulty){
        List<Question> questions=(List<Question>)questionRepo.findQuestionsByDifficulty(difficulty);
        for(Question question : questions) System.out.println(question.toString());
        List<Question> resultSet=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        int temp=n= Math.min(n, questions.size());
        while(n!=0){
            int randomNumber =(int)(Math.random() * ((temp-1) + 1));
            if(!set.contains(randomNumber)){
                set.add(randomNumber);
                resultSet.add(questions.get(randomNumber));
                n--;
            }
        }
        return ResponseEntity.status(200).body(resultSet);
    }


    /**
     * Returns ResponseEntity containing random n number of questions
     * If n is more than total number of questions then returns all questions
     * Found_Status header contains the number of questions retrieved
     */
    ResponseEntity<?> findNQuestions(int n){

        List<Question> resultSet=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        int temp=n=Math.min(n,questionRepo.findNumberOfQuestions());
        while(n!=0){
            int randomNumber = 1 + (int)(Math.random() * ((temp-1) + 1));
            if(!set.contains(randomNumber)){
                set.add(randomNumber);
                questionRepo.findById(n);
                resultSet.add(questionRepo.findById(n).get());
                n--;
            }
        }
        return ResponseEntity.status(200).header("Found_Status","questions found"+String.valueOf(temp)).body(resultSet);
    }


    /**
     * Calls calculateMarks() to calculate score and then updates the score in the DB
     * this score is associated to the current logged in user
     */
    int uploadMarks(QuizAnswers quizAnswers){
        int score=calculateMarks(quizAnswers);
        questionRepo.addScore(loginService.getUser().getUsername(),score);
        return score;
    }


    /**
     *method to calculate number of right answers
     * Assumes provided ID of questions is correct.Ensure this.
     */
    int calculateMarks(QuizAnswers quizAnswers){
        List<Integer> IDs=quizAnswers.getIds();
        List<String> answers=quizAnswers.getAnswers();
        int score=0;
        for(int i=0;i<IDs.size();i++){
            Integer ID=IDs.get(i);
            String ans=answers.get(i);
            if(questionRepo.findById(ID).get().getCorrectAns().equals(ans)) score++;
        }
        return score;
    }
}
