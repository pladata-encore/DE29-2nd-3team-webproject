package com.example.askproject.Service;

import java.util.List;
import java.util.Map;

import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;

public interface QuestionService {
    public List<QuestionDTO> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
    public void insertQuestion(QuestionDTO questionDTO);
    public List<joindQnaDTO> joinQuestionAnswerByQuestionTo(String questionTo, String userId);
    public void changeAnswered(Long questionId);
    public List<Map<String, Object>> countQuestionByUserId();
    public boolean checkMyQuestion(String userId, Long questionId);
    public boolean checkMyQuestionTo(String userId, Long questionId);
    public void updateQuestionContent(Long questionId, String questionContent);
}
