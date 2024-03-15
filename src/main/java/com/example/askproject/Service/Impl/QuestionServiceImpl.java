package com.example.askproject.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.AnswerDAO;
import com.example.askproject.Model.DAO.QuestionDAO;
import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;
import com.example.askproject.Model.Entity.AnswerEntity;
import com.example.askproject.Model.Entity.QuestionEntity;
import com.example.askproject.Service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public void insertQuestion(QuestionDTO questionDTO) {
        // TODO Auto-generated method stub
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setAnonymous(questionDTO.isAnonymous());
        questionEntity.setAnswered(questionDTO.isAnswered());
        questionEntity.setQuestionContent(questionDTO.getQuestionContent());
        questionEntity.setQuestionFrom(questionDTO.getQuestionFrom());
        questionEntity.setQuestionTo(questionDTO.getQuestionTo());;
        questionDAO.insertQuestion(questionEntity);
    }

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
            questionDTO.setAnonymous(questionEntity.isAnonymous());
            questionDTO.setAnswered(questionEntity.isAnswered());
            questionDTO.setQuestionContent(questionEntity.getQuestionContent());
            questionDTO.setQuestionFrom(questionEntity.getQuestionFrom());
            questionDTO.setQuestionId(questionEntity.getQuestionId());
            questionDTO.setQuestionTo(questionEntity.getQuestionTo());
            dtos.add(questionDTO);
        }
        return dtos; 
    }

    public void changeAnswered(Long questionId){ //답변 입력상태가 변경되면 Answered True->False 또는 반대로 변경
        QuestionEntity questionEntity = questionDAO.findByQuestionId(questionId);
        questionEntity.setAnswered(!questionEntity.isAnswered());
        questionDAO.updateQuestion(questionEntity);
    }

    public List<joindQnaDTO> joinQuestionAnswerByQuestionTo(String questionTo){
        List<joindQnaDTO> dtos = new ArrayList<>();
        List<QuestionEntity> entities = questionDAO.findAllByQuestionTo(questionTo);
        for (QuestionEntity questionEntity : entities) {
            AnswerEntity answerEntity = answerDAO.findByAnswerQuestionId(questionEntity.getQuestionId());
            joindQnaDTO qnaDTO = new joindQnaDTO();
            qnaDTO.setAnonymous(questionEntity.isAnonymous());
            qnaDTO.setAnswered(questionEntity.isAnswered());
            qnaDTO.setQuestionContent(questionEntity.getQuestionContent());
            qnaDTO.setQuestionFrom(questionEntity.getQuestionFrom());
            qnaDTO.setQuestionId(questionEntity.getQuestionId());
            qnaDTO.setQuestionTo(questionEntity.getQuestionTo());
            if (answerEntity != null){
                qnaDTO.setAnswerContent(answerEntity.getAnswerContent());
                qnaDTO.setAnswerFrom(answerEntity.getAnswerFrom());
                qnaDTO.setAnswerId(answerEntity.getAnswerId());
                qnaDTO.setAnswerQuestionId(answerEntity.getAnswerQuestionId());
                qnaDTO.setAnswerTo(answerEntity.getAnswerTo());
            }
            dtos.add(qnaDTO);
        }
        return dtos;
    }

    @Override
    public List<Map<String, Object>> countQuestionByUserId() {
        // TODO Auto-generated method stub
        return questionDAO.countQuestionByUserId();
    }
    
}
