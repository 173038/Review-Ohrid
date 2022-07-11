package com.example.reviewohrid.service;

import com.example.reviewohrid.DTO.UserDTO;
import com.example.reviewohrid.exceptions.UserValidateException;
import com.example.reviewohrid.model.User;

import java.util.ArrayList;

public interface AuthenticationService {

    boolean checkEmail(String email);

    boolean checkUserName(String username);

    boolean checkPassword(String password);

    ArrayList<String> validateAndSave(User user);

    User validateAndLogin(UserDTO userDTO) throws UserValidateException;

    User findByEmail(String email);

}
