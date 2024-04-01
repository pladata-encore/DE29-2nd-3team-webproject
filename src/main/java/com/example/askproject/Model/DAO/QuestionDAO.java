package com.example.askproject.Model.DAO;
import java.util.*;

import com.example.askproject.Model.Entity.QuestionEntity;

public interface QuestionDAO {
    public List<QuestionEntity> findAllByQuestionTo(String questionTo)throws Exception;
    public void deleteByQuestionId(Long questionId)throws Exception;
    public void insertQuestion(QuestionEntity questionEntity)throws Exception;
    public void updateQuestion(QuestionEntity questionEntity)throws Exception;
    public QuestionEntity findByQuestionId(Long questionId)throws Exception;
    public List<Map<String, Object>> countQuestionByUserId()throws Exception;
}
