package com.example.askproject.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.askproject.Model.Entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{
    public List<QuestionEntity> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
    public QuestionEntity findByQuestionId(Long questionId);
}
