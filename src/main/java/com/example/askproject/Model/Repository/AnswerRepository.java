package com.example.askproject.Model.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.askproject.Model.Entity.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long>{
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom);
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId);
    public void deleteByAnswerId(Long answerId);
}
