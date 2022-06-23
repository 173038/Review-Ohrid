package com.example.reviewohrid.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "creator", nullable = false, length = 45)
    private String creator;

    @Column(name = "modifier", nullable = false, length = 45)
    private String modifier;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @OneToMany
    @JoinColumn(name= "userid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Answer> answerList;

    public Question() {
    }

    public Question(Integer id, String creator, String modifier, String title, String question, Instant createdDate, List<Answer> answerList) {
        this.id = id;
        this.creator = creator;
        this.modifier = modifier;
        this.title = title;
        this.question = question;
        this.createdDate = createdDate;
        this.answerList = answerList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
