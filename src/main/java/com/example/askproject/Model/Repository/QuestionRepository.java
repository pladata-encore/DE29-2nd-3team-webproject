package com.example.askproject.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.askproject.Model.DTO.CountDTO;
import com.example.askproject.Model.Entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{
    public List<QuestionEntity> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
    public QuestionEntity findByQuestionId(Long questionId);

    @Query(
    value ="select question_from as user, count(*) as count from question group by question_from order by count desc limit 5",
    nativeQuery = true
    )
    public List<CountDTO> countQuestionByUserId();
}
