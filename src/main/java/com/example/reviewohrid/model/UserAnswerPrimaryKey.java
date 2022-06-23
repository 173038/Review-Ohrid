package com.example.reviewohrid.model;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.io.Serializable;

public class UserAnswerPrimaryKey implements Serializable {

    private User user;
    private Answer answer;

    public UserAnswerPrimaryKey() {
    }

    public UserAnswerPrimaryKey(User user, Answer answer) {
        this.user = user;
        this.answer = answer;
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
}
