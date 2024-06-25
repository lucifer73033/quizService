package Quiz_Service.quizService.DTO;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


/**
 * DTO Class for HTTP Request transfer and to add entries to the DataBase.
 * Doesnt necessarily need to implement Persistable. Better way is to leave username null so Spring Data doesnt attempt
 * to update non existent entries.
 */
@Table(name="users")
public class User implements Persistable<String> {
    @Id
    @Column("username")
    private String username;
    @Column("auth")
    private boolean auth;


    //Getters and Setters
    public boolean getAuth() {
        return auth;
    }

    public String getUsername() {
        return username;
    }

    public User(String username,boolean auth) {
        this.username = username;
        this.auth=auth;
    }

    public User() {
    }


    //Necessary Overridden functions implemented from Persistable interface
    @Override
    public String getId(){
        return this.username;
    }
    @Override
    public boolean isNew(){
        return true;
    }
}