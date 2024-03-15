package com.example.askproject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.askproject.Model.DTO.AnswerDTO;
import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;
import com.example.askproject.Service.AnswerService;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.QuestionService;
import com.example.askproject.Service.UserService;

@RequestMapping("/v1/qna")
@Controller
public class QnaContoller {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;

    @GetMapping("/page")
    public String getIndividualPage(@RequestParam String id, Model questionModel, Authentication authentication) {
        if (!userService.findAllUserId().contains(id)) {
            return "error";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        questionModel.addAttribute("myId", userDetails.getUsername());
        pageService.increasePageCount(id);

        List<joindQnaDTO> qdtos = questionService.joinQuestionAnswerByQuestionTo(id);
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
        return "mypage";
    }

    @PostMapping("/sendq")
    public String postQuestion(@ModelAttribute QuestionDTO questionDTO, @RequestParam String id,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        questionDTO.setQuestionTo(id);
        questionDTO.setQuestionFrom(userDetails.getUsername());
        questionService.insertQuestion(questionDTO);
        return "redirect:/v1/qna/page?id=" + id;
    }

    @PostMapping("/senda")
    public String postAnswer(@ModelAttribute AnswerDTO answerDTO, @RequestParam String pageid,
            @RequestParam("questionid") Long questionid, @RequestParam("from") String from,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        answerDTO.setAnswerTo(from);
        answerDTO.setAnswerQuestionId(questionid);
        answerDTO.setAnswerFrom(userDetails.getUsername());// 세션아이디가 될 예정
        answerService.insertAnswer(answerDTO);
        questionService.changeAnswered(questionid);
        return "redirect:/v1/qna/page?id=" + pageid;
    }

}
