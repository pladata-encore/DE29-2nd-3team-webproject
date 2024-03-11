package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.Entity.QuestionEntity;

public interface QuestionService {
    public List<QuestionEntity> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
}
