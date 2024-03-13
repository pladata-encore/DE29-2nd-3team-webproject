package com.example.askproject.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.askproject.Model.DTO.PageDTO;
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
    public String getMainPage(Model model){
        List<PageDTO> pageDTOs = pageService.findAllPage();
        List<PageDTO> selectedPages = pageDTOs.size() <= 5
                ? pageDTOs
                : getRandomPages(pageDTOs, 5);
        model.addAttribute("pages", selectedPages);
        return "main";
    }

    private List<PageDTO> getRandomPages(List<PageDTO> allPages, int count) {
        List<PageDTO> shuffledPages = new ArrayList<>(allPages);
        Collections.shuffle(shuffledPages);

        // count만큼 선택
        return shuffledPages.subList(0, count);
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }
    
}
