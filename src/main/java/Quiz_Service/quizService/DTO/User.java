package Quiz_Service.quizService.DTO;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table(name="users")
public class User implements Persistable<String> {
    @Id
    @Column("username")
    private String username;
    @Column("auth")
    private boolean auth;

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
    @Override
    public String getId(){
        return this.username;
    }
    @Override
    public boolean isNew(){
        return true;
    }
}