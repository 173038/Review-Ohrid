package com.example.reviewohrid.controller;

import com.example.reviewohrid.DTO.UserDTO;
import com.example.reviewohrid.exceptions.UserValidateException;
import com.example.reviewohrid.model.User;
import com.example.reviewohrid.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("")
    public String FirstPage(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/register")
    public String Register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String Login(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/register-successful")
    public String processRegister(User user, Model model)
    {
        ArrayList<String> res = authenticationService.validateAndSave(user);
        if (res.isEmpty())
        {
            return "login";
        }
        else
        {
            String result = String.join(", ", res);
            model.addAttribute("res", result);
            return "register";
        }
    }


    @PostMapping("/login-successful")
    @ResponseBody
    public ResponseEntity<User> processLogin(@RequestParam("email") String email, @RequestParam("password") String password)
            throws UserValidateException
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        User u = authenticationService.validateAndLogin(userDTO);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(u);
    }


    @GetMapping("/logout")
    public String logout()
    {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }
}