package com.example.reviewohrid.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "useranswerstatus")
public class UserAnswerStatus implements Serializable{

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Answer answer;

    private boolean status;

    public UserAnswerStatus() {
    }

    public UserAnswerStatus(User user, Answer answer, boolean status) {
        this.user = user;
        this.answer = answer;
        this.status = status;
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
