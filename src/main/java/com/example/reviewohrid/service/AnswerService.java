package com.example.reviewohrid.service;

import com.example.reviewohrid.DTO.AnswerDTO;
import com.example.reviewohrid.DTO.UserAnswerStatusDTO;
import com.example.reviewohrid.exceptions.InvalidAnswerException;
import com.example.reviewohrid.model.Answer;
import com.example.reviewohrid.model.UserAnswerStatus;

import java.util.ArrayList;

public interface AnswerService {

    boolean checkAnswer(String answer);

    boolean checkEmail(String email);

    Answer validateAndSave(AnswerDTO answerDTO) throws InvalidAnswerException;

    ArrayList<Answer> getAllAnswerForQuestion(Integer id);

    Integer getUserIdFromEmail(String email);

    ArrayList<UserAnswerStatus> checkIfPreviousVoted(Integer userId, Integer answerId);

    void insertNewUserAnswerStatus(Integer userId, Integer answerId, boolean status);

    void deleteUserAnswerStatus(Integer userId, Integer answerId);

    void updateUserAnswerStatus(Integer userId, Integer answerId, boolean status);

    void updateUpVotes(Integer newUpvote, Integer answerId);

    void updateDownVotes(Integer newDownVote, Integer answerId);

    void upVoteAnswer(UserAnswerStatusDTO userAnswerStatusDTO);

    void downVoteAnswer(UserAnswerStatusDTO userAnswerStatusDTO);

}
