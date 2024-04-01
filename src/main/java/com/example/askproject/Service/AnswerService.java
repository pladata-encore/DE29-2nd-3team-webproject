package com.example.askproject.Service;

import java.util.*;

import com.example.askproject.Model.DTO.AnswerDTO;

public interface AnswerService {
    public List<AnswerDTO> findAllByAnswerFrom(String answerTo)throws Exception;
    public AnswerDTO findByAnswerQuestionId(Long answerQuestionId)throws Exception;
    public void deleteByAnswerId(Long AnswerId)throws Exception;
    public void insertAnswer(AnswerDTO answerDTO)throws Exception;
    public List<Map<String, Object>> countAnswerByUserId()throws Exception;
    public Boolean checkMyAnswer(String userId, Long answerId)throws Exception;
    public void deleteAnswerCascade(Long answerQuestionId)throws Exception;
    public void updateAnswerContent(Long answerId, String answerContent)throws Exception;
}
