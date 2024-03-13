package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.askproject.Service.AnswerService;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.QuestionService;
import com.example.askproject.Service.UserService;


@RequestMapping("/v1")
@Controller
public class MainController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/main")
    public String getMainPage(){
        return "main";
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }
    
}
