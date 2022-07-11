package com.example.reviewohrid.service;

import com.example.reviewohrid.model.UserAnswerStatus;

import java.util.ArrayList;

public interface UserAnswerStatusService {

    ArrayList<UserAnswerStatus> getAllByUserId(Integer id);

}
