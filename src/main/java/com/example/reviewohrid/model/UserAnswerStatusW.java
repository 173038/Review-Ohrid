//package com.example.reviewohrid.model;
//
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "useranswerstatus")
//@IdClass(UserAnswerPrimaryKey.class)
//public class UserAnswerStatus implements Serializable{
//
//
//    @Id
//    @JoinColumn(name = "id")
//    @ManyToOne
//    private User user;
//
//    @Id
//    @JoinColumn(name = "answer_id")
//    @ManyToOne
//    private Answer answer;
//
//    private boolean status;
//
//    public UserAnswerStatus(Integer userId, Integer answerId, boolean status) {
//    }
//
//    public UserAnswerStatus(User user, Answer answer, boolean status) {
//        this.user = user;
//        this.answer = answer;
//        this.status = status;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Answer getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(Answer answer) {
//        this.answer = answer;
//    }
//
//
//}
//
//
////import com.example.reviewohrid.model.Answer;
////import com.example.reviewohrid.model.User;
////import com.example.reviewohrid.model.UserAnswerPrimaryKey;
////import org.springframework.data.jpa.domain.support.AuditingEntityListener;
////
////import javax.persistence.*;
////
////@Entity
////@Table(name = "userAnswerStatus")
////@EntityListeners(AuditingEntityListener.class)
////@IdClass(UserAnswerPrimaryKey.class)
////public class UserAnswerStatus
////{
////    @Id
////    @ManyToOne
////    @JoinColumn(name = "userId", nullable = false)
////    private User user;
////
////    @Id
////    @ManyToOne
////    @JoinColumn(name = "answerId", nullable = false)
////    private Answer answer;
////
////    @Column(name = "status", nullable = false)
////    private boolean status;
////
////    public User getUser()
////    {
////        return user;
////    }
////
////    public void setUser(User user)
////    {
////        this.user = user;
////    }
////
////    public Answer getAnswer()
////    {
////        return answer;
////    }
////
////    public void setAnswer(Answer answer)
////    {
////        this.answer = answer;
////    }
////
////    public boolean isStatus()
////    {
////        return status;
////    }
////
////    public void setStatus(boolean status)
////    {
////        this.status = status;
////    }
////}
