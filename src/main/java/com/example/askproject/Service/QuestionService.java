package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;

public interface QuestionService {
    public List<QuestionDTO> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
    public void insertQuestion(QuestionDTO questionDTO);
    public List<joindQnaDTO> joinQuestionAnswerByQuestionTo(String questionTo);
    public void changeAnswered(Long questionId);
}
