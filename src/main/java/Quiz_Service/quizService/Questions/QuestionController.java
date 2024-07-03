package Quiz_Service.quizService.Questions;
import Quiz_Service.quizService.DTO.Question;
import Quiz_Service.quizService.DTO.QuizAnswers;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * RestController class to handle all Question management related HTTP Requests
 */
@RestController
@Validated
public class QuestionController {
    private final QuestionService questionService;//to handle all the data processing needs

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    /**
     * Adds the provided question. Can only be added if the logged in user is a Teacher.
     * @param question: a Question class type JSON object
     * @return none
     */
    @PostMapping("/question")
    void addQuestion(@Valid @RequestBody Question question, Errors errors){
        if(errors.hasErrors()) System.out.println("bababababaa");
        questionService.addQuestion(question);
    }


    /**
     * HTTP Request to update an existing question
     * @param question: Question type JSON Object. Make sure to provide the correct id of the question.
     * @return none
     */
    @PutMapping("/question")
    void updateQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
    }


    /**
     * Http request to delete a question by ID.
     * @param n: ID of the question
     * @return none
     */
    @DeleteMapping("/question")
    void deleteById(@RequestParam int n){
        questionService.deleteById(n);
    }


    /**
     * Http request to find a specified number of questions selected based on difficulty if required
     * @param n,difficuly (n:number of questions,difficulty: optional parameter to find question based on difficulty)
     * @return ResponseEntity containing a List of Question objects. If 'n' more than total questions, all questions returned
     * @return Found_Status Header contains number of questions found
     */
    @GetMapping("/question")
    ResponseEntity<?> findQuestions(@RequestParam int n,@RequestParam(required = false) String difficulty){
        if(difficulty==null) return ResponseEntity.status(200).body(questionService.findNQuestions(n));
        return ResponseEntity.status(200).body(questionService.findNQuestionsByDifficulty(n,difficulty));
    }


    /**
     * Http request to upload the answers of a quiz
     * @param quizAnswers: QuizAnswers type JSON Object.
     * @return score obtained in current quiz.
     */
    @PostMapping("/submit")
    ResponseEntity<?> uploadMarks(@RequestBody QuizAnswers quizAnswers){
        System.out.println(quizAnswers.getAnswers());
        System.out.println(quizAnswers.getIds());
        return ResponseEntity.status(200).body(questionService.uploadMarks(quizAnswers));
    }
}
