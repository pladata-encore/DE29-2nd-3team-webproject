package com.example.askproject.Model.DAO;
import java.util.*;
import com.example.askproject.Model.Entity.AnswerEntity;

public interface AnswerDAO {
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom);
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId);
    public void deleteByAnswerId(Long AnswerId);
    public void insertAnswer(AnswerEntity answerEntity);
    public List<Map<String, Object>> countAnswerByUserId();
}
