package Quiz_Service.quizService.DTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Entity DTO Class for storing data into the DataBase and also for HTTP Requests
 */
@Table("questions")
public class Question{


    /**
     * Need to assign unique IDs to questions and not use AUTO_INCREMENT feature of SQL
     * because it creates discrepancies when deleting entries
     */
    @Id
    @Column("id")
    Integer id;
    @Column("question")
    String question;
    @Column("optionA")
    String optionA;
    @Column("optionB")
    String optionB;
    @Column("correctAns")
    String correctAns;
    @Column("difficulty")
    String difficulty;


    //Personal use Constructor(Not Required)
    public Question(String question, String optionA, String optionB, String correctAns, String difficulty) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.correctAns = correctAns;
        this.difficulty = difficulty;
    }


    //Getters and Setters
    public Integer getId(){
        return this.id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String getDifficulty() {
        return difficulty;
    }



    //toString method for debugging purposes(Not Required)
    @Override
    public String toString(){
        return "Question: "+question+" optionA: "+optionA+" optionB: "+optionB+" correctAns: "+correctAns+" difficulty: "+difficulty;
    }
}
