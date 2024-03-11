package com.example.askproject.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.AnswerDAO;
import com.example.askproject.Model.DTO.AnswerDTO;
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
    public List<AnswerDTO> findAllByAnswerFrom(String answerFrom) {
        // TODO Auto-generated method stub
        List<AnswerEntity> entities = answerDAO.findAllByAnswerFrom(answerFrom);
        List<AnswerDTO> dtos = new ArrayList<>();
        for (AnswerEntity answerEntity : entities) {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setAnswerContent(answerEntity.getAnswerContent());
            answerDTO.setAnswerFrom(answerEntity.getAnswerFrom());
            answerDTO.setAnswerId(answerEntity.getAnswerId());
            answerDTO.setAnswerQuestionId(answerEntity.getAnswerQuestionId());
            answerDTO.setAnswerTo(answerEntity.getAnswerTo());
            dtos.add(answerDTO);
        }
        return dtos;
    }

    @Override
    public AnswerDTO findByAnswerQuestionId(Long answerQuestionId) {
        // TODO Auto-generated method stub
        AnswerEntity answerEntity = answerDAO.findByAnswerQuestionId(answerQuestionId);
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerContent(answerEntity.getAnswerContent());
        answerDTO.setAnswerFrom(answerEntity.getAnswerFrom());
        answerDTO.setAnswerId(answerEntity.getAnswerId());
        answerDTO.setAnswerQuestionId(answerEntity.getAnswerQuestionId());
        answerDTO.setAnswerTo(answerEntity.getAnswerTo());
        return answerDTO;
    }
    
}
