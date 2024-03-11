package com.example.askproject.Model.DAO;
import java.util.List;
import com.example.askproject.Model.Entity.AnswerEntity;

public interface AnswerDAO {
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom);
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId);
    public void deleteByAnswerId(Long AnswerId);
}
