package Quiz_Service.quizService.Questions;

import Quiz_Service.quizService.DTO.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping("/question")
    void addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
    }
    @PutMapping("/question")
    void updateQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
    }
    @DeleteMapping("/question")
    void deleteById(@RequestParam int n){
        questionService.deleteById(n);
    }
    @GetMapping("/question")
    ResponseEntity<?> findQuestions(@RequestParam int n,@RequestParam(required = false) String difficulty){
        if(difficulty==null) return ResponseEntity.status(200).body(questionService.findNQuestions(n));
        return ResponseEntity.status(200).body(questionService.findNQuestionsByDifficulty(n,difficulty));
    }
}
