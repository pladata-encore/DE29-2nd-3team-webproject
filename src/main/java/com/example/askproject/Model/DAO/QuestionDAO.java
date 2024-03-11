package com.example.askproject.Model.DAO;
import java.util.List;
import com.example.askproject.Model.Entity.QuestionEntity;

public interface QuestionDAO {
    public List<QuestionEntity> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
}
