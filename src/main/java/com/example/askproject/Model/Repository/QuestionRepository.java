package com.example.askproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.askproject.Model.DTO.joindQnaDTO;
import com.example.askproject.Model.Entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{
    public List<QuestionEntity> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
    public QuestionEntity findByQuestionId(Long questionId);
}
