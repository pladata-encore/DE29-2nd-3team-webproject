package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.Entity.AnswerEntity;

public interface AnswerService {
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom);
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId);
    public void deleteByAnswerId(Long AnswerId);
}
