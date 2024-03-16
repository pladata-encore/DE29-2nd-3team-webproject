package com.example.askproject.Model.DAO.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.askproject.Model.DAO.AnswerDAO;
import com.example.askproject.Model.Entity.AnswerEntity;
import com.example.askproject.Model.Repository.AnswerRepository;

@Service
public class AnswerDAOImpl implements AnswerDAO{
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    @Transactional
    public void deleteByAnswerId(Long answerId) {
        // TODO Auto-generated method stub
        answerRepository.deleteByAnswerId(answerId);   
    }

    @Override
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom) {
        // TODO Auto-generated method stub
        return answerRepository.findAllByAnswerFrom(answerFrom);
    }

    @Override
    public void insertAnswer(AnswerEntity answerEntity) {
        // TODO Auto-generated method stub
        answerRepository.save(answerEntity);
        
    }

    @Override
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId) {
        // TODO Auto-generated method stub
        return answerRepository.findByAnswerQuestionId(answerQuestionId);
    }

    @Override
    public List<Map<String, Object>> countAnswerByUserId() {
        // TODO Auto-generated method stub
        return answerRepository.countAnswerByUserId();
    }

    @Override
    public AnswerEntity findByAnswerId(Long answerId) {
        // TODO Auto-generated method stub
        return answerRepository.findByAnswerId(answerId);
    }

    @Override
    public void updateAnswer(AnswerEntity answerEntity) {
        // TODO Auto-generated method stub
        answerRepository.save(answerEntity);
    }
    
}
