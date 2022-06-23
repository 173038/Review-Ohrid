package com.example.reviewohrid.service;

import com.example.reviewohrid.DTO.QuestionDTO;
import com.example.reviewohrid.DTO.UserQuestionDTO;
import com.example.reviewohrid.exceptions.InvalidCreatorException;
import com.example.reviewohrid.model.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {

    boolean checkTitle(String email);

    boolean checkQuestion(String question);

    boolean checkEmail(String email);

    ArrayList<String> validateAndPost(QuestionDTO questionDTO);

    List<Question>getAllQuestions();

    void deleteQuestion(UserQuestionDTO userQuestionDTO) throws InvalidCreatorException;

}
