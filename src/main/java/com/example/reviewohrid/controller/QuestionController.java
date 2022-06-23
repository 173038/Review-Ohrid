package com.example.reviewohrid.controller;

import com.example.reviewohrid.DTO.QuestionDTO;
import com.example.reviewohrid.DTO.UserQuestionDTO;
import com.example.reviewohrid.exceptions.InvalidCreatorException;
import com.example.reviewohrid.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController
{
    @Autowired
    private QuestionService questionService;

    @PostMapping("/ask-question")
    @ResponseBody
    public ResponseEntity<String> askQuestion(@RequestBody QuestionDTO questionDTO)
    {
        ArrayList<String> res = questionService.validateAndPost(questionDTO);
        if (res.isEmpty())
        {
            return ResponseEntity.ok("Success");
        }
        else
        {
            String result = String.join("<br>", res);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @RequestMapping(value = "/delete-question", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteQuestion(@RequestBody UserQuestionDTO userQuestionDTO) throws InvalidCreatorException
    {
        questionService.deleteQuestion(userQuestionDTO);
        return ResponseEntity.ok("Success");
    }
}