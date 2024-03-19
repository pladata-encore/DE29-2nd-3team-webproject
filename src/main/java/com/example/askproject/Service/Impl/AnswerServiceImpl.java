package com.example.askproject.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.AnswerDAO;
import com.example.askproject.Model.DAO.UserDAO;
import com.example.askproject.Model.DTO.AnswerDTO;
import com.example.askproject.Model.Entity.AnswerEntity;
import com.example.askproject.Service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    private UserDAO userDAO;

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
        if (answerEntity == null) {
            return new AnswerDTO();
        }
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerContent(answerEntity.getAnswerContent());
        answerDTO.setAnswerFrom(answerEntity.getAnswerFrom());
        answerDTO.setAnswerId(answerEntity.getAnswerId());
        answerDTO.setAnswerQuestionId(answerEntity.getAnswerQuestionId());
        answerDTO.setAnswerTo(answerEntity.getAnswerTo());
        return answerDTO;
    }

    @Override
    public void insertAnswer(AnswerDTO answerDTO) {
        // TODO Auto-generated method stub
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerContent(answerDTO.getAnswerContent());
        answerEntity.setAnswerFrom(answerDTO.getAnswerFrom());
        answerEntity.setAnswerQuestionId(answerDTO.getAnswerQuestionId());
        answerEntity.setAnswerTo(answerDTO.getAnswerTo());
        answerDAO.insertAnswer(answerEntity);
    }

    @Override
    public List<Map<String, Object>> countAnswerByUserId() {
        // TODO Auto-generated method stub
        List<Map<String, Object>> realUser = new ArrayList<>();
        List<Map<String, Object>> answerCountList = answerDAO.countAnswerByUserId();
        for (Map<String, Object> answerCount : answerCountList) {
            if (userDAO.findAllUserId().contains(answerCount.get("user"))){
                realUser.add(answerCount);
            }
        }
        return realUser;
    }

    @Override
    public Boolean checkMyAnswer(String userId, Long answerId) {
        // TODO Auto-generated method stub
        AnswerEntity entity = answerDAO.findByAnswerId(answerId);
        return entity.getAnswerFrom().equals(userId);
    }

    @Override
    public void deleteAnswerCascade(Long answerQuestionId) {
        // TODO Auto-generated method stub
        AnswerEntity answerEntity = answerDAO.findByAnswerQuestionId(answerQuestionId);
        if (answerEntity != null){
            answerDAO.deleteByAnswerId(answerEntity.getAnswerId());}
        
    }
    @Override
    public void updateAnswerContent(Long answerId, String answerContent) {
        // TODO Auto-generated method stub
        AnswerEntity entity = answerDAO.findByAnswerId(answerId);
        entity.setAnswerContent(answerContent);
        answerDAO.updateAnswer(entity);
    }
}
