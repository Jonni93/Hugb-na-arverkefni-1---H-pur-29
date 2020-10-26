package project.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String uName;
    private String password;
    private String email;

    @ElementCollection(targetClass=Categories.class)
    @Column(name="categories", nullable=false)
    @CollectionTable(name="user_categories", joinColumns= {@JoinColumn(name="user_id")})
    private List<Categories> categories = new ArrayList<>();

    public User(){

    }

    public User(String uName, String password) {
        this.uName = uName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
