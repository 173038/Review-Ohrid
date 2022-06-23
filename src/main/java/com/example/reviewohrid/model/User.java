package com.example.reviewohrid.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "user")
    private List<UserAnswerStatus> userAnswerStatusList;

    public User() {
    }

    public User(Integer id, String email, String password, String username, Integer age, List<UserAnswerStatus> userAnswerStatusList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.userAnswerStatusList = userAnswerStatusList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<UserAnswerStatus> getUserAnswerStatusList() {
        return userAnswerStatusList;
    }

    public void setUserAnswerStatusList(List<UserAnswerStatus> userAnswerStatusList) {
        this.userAnswerStatusList = userAnswerStatusList;
    }
}
