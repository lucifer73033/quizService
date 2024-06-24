package Quiz_Service.quizService.Questions;

import Quiz_Service.quizService.Utilities.Annotations.CheckUserStatus;
import Quiz_Service.quizService.DTO.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Service
public class QuestionService {
    private final QuestionRepo questionRepo;


    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;

    }
    @CheckUserStatus
    void addQuestion(Question question){
         questionRepo.save(question);
    }
    @CheckUserStatus
    void deleteById(Integer id){
        questionRepo.deleteById(id);
    }
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
}
