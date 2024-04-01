package com.example.askproject.Model.DAO.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.askproject.Model.DAO.QuestionDAO;
import com.example.askproject.Model.Entity.QuestionEntity;
import com.example.askproject.Model.Repository.QuestionRepository;

@Service
public class QuestionDAOImpl implements QuestionDAO{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void insertQuestion(QuestionEntity questionEntity) throws Exception{
        // TODO Auto-generated method stub
        questionRepository.save(questionEntity);
    }

    @Override
    @Transactional
    public void deleteByQuestionId(Long questionId) throws Exception{
        // TODO Auto-generated method stub
        questionRepository.deleteByQuestionId(questionId);
    }

    @Override
    public List<QuestionEntity> findAllByQuestionTo(String questionTo) throws Exception{
        // TODO Auto-generated method stub
        return questionRepository.findAllByQuestionTo(questionTo);
    }

    @Override
    public QuestionEntity findByQuestionId(Long questionId) throws Exception{
        // TODO Auto-generated method stub
        return questionRepository.findByQuestionId(questionId);
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) throws Exception{
        // TODO Auto-generated method stub
        questionRepository.save(questionEntity);
    }

    @Override
    public List<Map<String, Object>> countQuestionByUserId() throws Exception{
        // TODO Auto-generated method stub
        return questionRepository.countQuestionByUserId();
    }
}
