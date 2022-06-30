package com.example.reviewohrid.controller;

import com.example.reviewohrid.DTO.AnswerDTO;
import com.example.reviewohrid.DTO.UserAnswerDTO;
import com.example.reviewohrid.DTO.UserAnswerStatusDTO;
import com.example.reviewohrid.DTO.UserQuestionDTO;
import com.example.reviewohrid.exceptions.InvalidAnswerException;
import com.example.reviewohrid.exceptions.InvalidCreatorException;
import com.example.reviewohrid.model.Answer;
import com.example.reviewohrid.model.User;
import com.example.reviewohrid.model.UserAnswerStatus;
import com.example.reviewohrid.service.AnswerService;
import com.example.reviewohrid.service.AuthenticationService;
import com.example.reviewohrid.service.UserAnswerStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController
{

    private final AnswerService answerService;

    private final AuthenticationService authenticationService;

    private final UserAnswerStatusService userAnswerStatusService;

    public AnswerController(AnswerService answerService, AuthenticationService authenticationService, UserAnswerStatusService userAnswerStatusService) {
        this.answerService = answerService;
        this.authenticationService = authenticationService;
        this.userAnswerStatusService = userAnswerStatusService;
    }

    @GetMapping("/{userId}/view-answer/{questionId}")
    public String ViewAnswer(@PathVariable String questionId, @PathVariable String userId, Model model, Authentication authentication)
    {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
        {
            return "login";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = authenticationService.findByEmail(userDetails.getUsername());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userId", user.getId());
        model.addAttribute("id", questionId);
        ArrayList<Answer> answerList = answerService.getAllAnswerForQuestion(Integer.parseInt(questionId));
        ArrayList<UserAnswerStatus> userAnswerStatusArrayList = userAnswerStatusService.getAllByUserId(Integer.parseInt(userId));
        model.addAttribute("userAnswerStatusArrayList", userAnswerStatusArrayList);
        model.addAttribute("answerList", answerList);
        return "viewAnswer";
    }

//    @PostMapping("/answer-successful")
//    @ResponseBody
//    public ResponseEntity<Answer> postAnswer(@RequestBody AnswerDTO answerDTO) throws InvalidAnswerException
//    {
//        Answer answer = answerService.validateAndSave(answerDTO);
//        return ResponseEntity.ok(answer);
//    }

    @PostMapping("/answer-successful")
    @ResponseBody
    public ResponseEntity<String> postAnswer(@RequestBody AnswerDTO answerDTO) throws InvalidAnswerException
    {
        Answer answer = answerService.validateAndSave(answerDTO);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/up-vote-answer")
    @ResponseBody
    public ResponseEntity<String> upVoteAnswer(@RequestBody UserAnswerStatusDTO userAnswerStatusDTO)
    {
        answerService.upVoteAnswer(userAnswerStatusDTO);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/down-vote-answer")
    @ResponseBody
    public ResponseEntity<String> downVoteAnswer(@RequestBody UserAnswerStatusDTO userAnswerStatusDTO)
    {
        answerService.downVoteAnswer(userAnswerStatusDTO);
        return ResponseEntity.ok("OK");
    }
    @RequestMapping(value = "/delete-answer", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteAnswer(@RequestBody UserAnswerDTO userAnswerDTO) throws InvalidCreatorException
    {
        answerService.deleteAnswer(userAnswerDTO);
        return ResponseEntity.ok("Success");
    }
}
