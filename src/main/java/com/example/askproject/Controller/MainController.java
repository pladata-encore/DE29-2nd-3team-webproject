package com.example.askproject.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Service.AnswerService;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.QuestionService;
import com.example.askproject.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/v1")
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
    public String getMainPage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("myId", userDetails.getUsername());
        List<Map<String, Object>> countQuestions = questionService.countQuestionByUserId();
        List<Map<String, Object>> countAnswers = questionService.countQuestionByUserId();
        List<PageDTO> pageDTOs = pageService.findAllPage();
        List<PageDTO> selectedPages = pageDTOs.size() <= 5
                ? pageDTOs
                : getRandomPages(pageDTOs, 5);
        model.addAttribute("countQuestion", countQuestions);
        model.addAttribute("countQuestion", countAnswers);
        model.addAttribute("pages", selectedPages);
        return "main";
    }

    private List<PageDTO> getRandomPages(List<PageDTO> allPages, int count) {
        List<PageDTO> shuffledPages = new ArrayList<>(allPages);
        Collections.shuffle(shuffledPages);

        // count만큼 선택
        return shuffledPages.subList(0, count);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<String> searchUsers(@RequestParam("query") String query) {
        return pageService.findByPageIdContaining(query);
    }

}
