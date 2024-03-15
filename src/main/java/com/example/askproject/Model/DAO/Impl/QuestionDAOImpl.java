package com.example.askproject.Model.DAO.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.QuestionDAO;
import com.example.askproject.Model.Entity.QuestionEntity;
import com.example.askproject.Model.Repository.QuestionRepository;

@Service
public class QuestionDAOImpl implements QuestionDAO{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void insertQuestion(QuestionEntity questionEntity) {
        // TODO Auto-generated method stub
        questionRepository.save(questionEntity);
    }

    @Override
    public void deleteByQuestionId(Long questionId) {
        // TODO Auto-generated method stub
        questionRepository.deleteByQuestionId(questionId);
    }

    @Override
    public List<QuestionEntity> findAllByQuestionTo(String questionTo) {
        // TODO Auto-generated method stub
        return questionRepository.findAllByQuestionTo(questionTo);
    }

    @Override
    public QuestionEntity findByQuestionId(Long questionId) {
        // TODO Auto-generated method stub
        return questionRepository.findByQuestionId(questionId);
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) {
        // TODO Auto-generated method stub
        questionRepository.save(questionEntity);
    }

    @Override
    public List<Map<String, Object>> countQuestionByUserId() {
        // TODO Auto-generated method stub
        return questionRepository.countQuestionByUserId();
    }
}
