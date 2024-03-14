package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.askproject.Model.Repository.UserRepository;

@Controller
@RequestMapping("/v1")
public class LoginController {


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

}
