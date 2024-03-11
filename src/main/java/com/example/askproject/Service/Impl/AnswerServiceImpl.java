package com.example.askproject.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.AnswerDAO;
import com.example.askproject.Model.Entity.AnswerEntity;
import com.example.askproject.Service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public void deleteByAnswerId(Long AnswerId) {
        // TODO Auto-generated method stub
        answerDAO.deleteByAnswerId(AnswerId);
    }

    @Override
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom) {
        // TODO Auto-generated method stub
        return answerDAO.findAllByAnswerFrom(answerFrom);
    }

    @Override
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId) {
        // TODO Auto-generated method stub
        return answerDAO.findByAnswerQuestionId(answerQuestionId);
    }
    
}
