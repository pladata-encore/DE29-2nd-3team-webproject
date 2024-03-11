package com.example.askproject.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.QuestionDAO;
import com.example.askproject.Model.Entity.QuestionEntity;
import com.example.askproject.Service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public void deleteByQuestionId(Long questionId) {
        // TODO Auto-generated method stub
        questionDAO.deleteByQuestionId(questionId);
    }

    @Override
    public List<QuestionEntity> findAllByQuestionTo(String questionTo) {
        // TODO Auto-generated method stub
        return questionDAO.findAllByQuestionTo(questionTo);
    }
    
}
