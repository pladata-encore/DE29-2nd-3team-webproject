package com.example.askproject.Model.DAO;
import java.util.List;

import com.example.askproject.Model.DTO.CountDTO;
import com.example.askproject.Model.Entity.QuestionEntity;

public interface QuestionDAO {
    public List<QuestionEntity> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
    public void insertQuestion(QuestionEntity questionEntity);
    public void updateQuestion(QuestionEntity questionEntity);
    public QuestionEntity findByQuestionId(Long questionId);
    public List<CountDTO> countQuestionByUserId();
}
