package com.example.askproject.Model.Repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.askproject.Model.Entity.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long>{
    public List<AnswerEntity> findAllByAnswerFrom(String answerFrom);
    public AnswerEntity findByAnswerQuestionId(Long answerQuestionId);
    public void deleteByAnswerId(Long answerId);

    @Query(
    value ="select answer_from as user, count(*) as count from answer group by question_to order by count desc limit 5",
    nativeQuery = true
    )
    public List<Map<String, Object>> countAnswerByUserId();
}
