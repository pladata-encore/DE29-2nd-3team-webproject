package com.example.askproject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Service.AnswerService;
import com.example.askproject.Service.QuestionService;

@RequestMapping("/v1/qna")
@Controller
public class QnaContoller {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/page/")
    public String getIndividualPage(@RequestParam String id){
        List<QuestionDTO> dtos = questionService.findAllByQuestionTo(id);
        return null;
    }


}
