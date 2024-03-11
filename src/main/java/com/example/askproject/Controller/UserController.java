package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Service.UserService;

import jakarta.validation.Valid;



@RequestMapping("v1/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/regist")
    public String registUser() {
        return "regist";
    }
    
    @PostMapping("/regist")
    public String registUser(@Valid @ModelAttribute UserDTO dto) {
        //TODO: process POST request
        userService.registUser(dto);
        return "redirect:/v1/user/login";
    }
    
}
