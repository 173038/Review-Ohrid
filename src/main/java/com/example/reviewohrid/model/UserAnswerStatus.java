package com.example.reviewohrid.model;

import javax.persistence.*;

@Entity
@Table(name = "useranswerstatus")
public class UserAnswerStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Answer answer;

    private boolean status;


    public UserAnswerStatus() {
    }

    public Integer getId() {
        return statusId;
    }

    public void setId(Integer id) {
        this.statusId = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
