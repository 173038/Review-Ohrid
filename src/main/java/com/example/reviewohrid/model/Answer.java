package com.example.reviewohrid.model;

import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="answer")
public class Answer implements Comparable<Answer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String creator;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Integer upvotes;

    @Column(nullable = false)
    private Integer downvotes;

    @ManyToOne
    private Question question;

    @OneToMany
    @JoinColumn(name = "users")
    private List<UserAnswerStatus> userAnswerStatusList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer() {
    }

    public Answer(Integer answerId, String answer, String creator, Instant createdDate, Integer upvotes, Integer downvotes, Question question, List<UserAnswerStatus> userAnswerStatusList) {
        this.answerId = answerId;
        this.answer = answer;
        this.creator = creator;
        this.createdDate = createdDate;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.question = question;
        this.userAnswerStatusList = userAnswerStatusList;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<UserAnswerStatus> getUserAnswerStatusList() {
        return userAnswerStatusList;
    }

    public void setUserAnswerStatusList(List<UserAnswerStatus> userAnswerStatusList) {
        this.userAnswerStatusList = userAnswerStatusList;
    }

    @Override
    public int compareTo(Answer o) {
        return 0;
    }
}

