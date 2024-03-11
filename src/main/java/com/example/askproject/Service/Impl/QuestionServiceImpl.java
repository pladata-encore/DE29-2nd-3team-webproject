package com.example.askproject.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.QuestionDAO;
import com.example.askproject.Model.DTO.QuestionDTO;
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
    public List<QuestionDTO> findAllByQuestionTo(String questionTo) {
        // TODO Auto-generated method stub
        List<QuestionDTO> dtos = new ArrayList<>();
        List<QuestionEntity> entities = questionDAO.findAllByQuestionTo(questionTo);
        for (QuestionEntity questionEntity : entities) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setAnonymous(questionEntity.getAnonymous());
            questionDTO.setAnswered(questionEntity.getAnswered());
            questionDTO.setQuestionContent(questionEntity.getQuestionContent());
            questionDTO.setQuestionFrom(questionEntity.getQuestionFrom());
            questionDTO.setQuestionId(questionEntity.getQuestionId());
            questionDTO.setQuestionTo(questionEntity.getQuestionTo());
            dtos.add(questionDTO);
        }
        return dtos; 
    }
    
}
