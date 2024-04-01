package com.example.askproject.Model.DAO;
import java.util.*;
import com.example.askproject.Model.Entity.AnswerEntity;

public interface AnswerDAO {
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom)throws Exception;
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId)throws Exception;
    public void deleteByAnswerId(Long AnswerId)throws Exception;
    public void insertAnswer(AnswerEntity answerEntity)throws Exception;
    public List<Map<String, Object>> countAnswerByUserId()throws Exception;
    public AnswerEntity findByAnswerId(Long answerId)throws Exception;
    public void updateAnswer(AnswerEntity answerEntity)throws Exception;
}
