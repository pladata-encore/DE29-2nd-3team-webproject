package com.example.askproject.Service;

import java.util.List;
import java.util.Map;

import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;

public interface QuestionService {
    public List<QuestionDTO> findAllByQuestionTo(String questionTo)throws Exception;
    public void deleteByQuestionId(Long questionId)throws Exception;
    public void insertQuestion(QuestionDTO questionDTO)throws Exception;
    public List<joindQnaDTO> joinQuestionAnswerByQuestionTo(String questionTo, String userId)throws Exception;
    public void changeAnswered(Long questionId)throws Exception;
    public List<Map<String, Object>> countQuestionByUserId()throws Exception;
    public boolean checkMyQuestion(String userId, Long questionId)throws Exception;
    public boolean checkMyQuestionTo(String userId, Long questionId)throws Exception;
    public void updateQuestionContent(Long questionId, String questionContent)throws Exception;
}
