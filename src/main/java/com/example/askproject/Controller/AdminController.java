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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;
import com.example.askproject.Service.AnswerService;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.QuestionService;
import com.example.askproject.Service.UserService;
import com.example.askproject.Service.UserServiceSecurity;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserServiceSecurity userServiceSecurity;

    @GetMapping("/main")
    public String getMainPage(Model model, Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("myId", userDetails.getUsername());
        model.addAttribute("Nickname", userService.findNicknameByUserId(userDetails.getUsername()));
        List<Map<String, Object>> countQuestions = questionService.countQuestionByUserId();
        List<Map<String, Object>> countAnswers = answerService.countAnswerByUserId();
        List<PageDTO> pageDTOs = pageService.findAllPage();
        List<PageDTO> selectedPages = pageDTOs.size() <= 5
                ? pageDTOs
                : getRandomPages(pageDTOs, 5);
        model.addAttribute("countQuestion", countQuestions);
        model.addAttribute("countAnswer", countAnswers);
        model.addAttribute("pages", selectedPages);
        return "admin/main";
    }

    private List<PageDTO> getRandomPages(List<PageDTO> allPages, int count) throws Exception{
        List<PageDTO> shuffledPages = new ArrayList<>(allPages);
        Collections.shuffle(shuffledPages);

        // count만큼 선택
        return shuffledPages.subList(0, count);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<String> searchUsers(@RequestParam("query") String query) throws Exception{
        return pageService.findByPageIdContaining(query);
    }

    @GetMapping("/page")
    public String getIndividualPage(@RequestParam String id, Model questionModel, Authentication authentication) throws Exception{
        if (!userService.findAllUserId().contains(id)) {
            return "error";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        questionModel.addAttribute("Nickname", userService.findNicknameByUserId(userDetails.getUsername()));
        questionModel.addAttribute("myId", userDetails.getUsername());
        pageService.increasePageCount(id);

        List<joindQnaDTO> qdtos = questionService.joinQuestionAnswerByQuestionTo(id, userDetails.getUsername());
        PageDTO pageDTO = pageService.findByPageId(id);
        questionModel.addAttribute("pageTitle", pageDTO.getPageTitle());
        questionModel.addAttribute("pageComment", pageDTO.getPageComment());
        questionModel.addAttribute("pageTodayCount", pageDTO.getPageTodayCount());
        int questionCount = qdtos.size();
        int answerCount = answerService.findAllByAnswerFrom(id).size();
        boolean isEquals = id.equals(userDetails.getUsername()); // to1을 세션 아이디로 바꿔야댐
        questionModel.addAttribute("questionCount", questionCount);
        questionModel.addAttribute("answerCount", answerCount);
        questionModel.addAttribute("noAnswerCount", questionCount - answerCount);
        questionModel.addAttribute("idCheck", isEquals);
        questionModel.addAttribute("pageId", id);
        questionModel.addAttribute("questions", qdtos);
        return "admin/mypage";
    }

    @PostMapping("/deletea")
    public void deleteAnswer(@RequestParam Long answerId, @RequestParam Long questionId,
            Authentication authentication) throws Exception{
        // TODO: process POST request
        answerService.deleteByAnswerId(answerId);
        questionService.changeAnswered(questionId);
    }

    @PostMapping("/deleteq")
    public void deleteQuestion(@RequestParam Long questionId, Authentication authentication) throws Exception{
        // TODO: process POST request
        questionService.deleteByQuestionId(questionId);
        answerService.deleteAnswerCascade(questionId);
    }
}
