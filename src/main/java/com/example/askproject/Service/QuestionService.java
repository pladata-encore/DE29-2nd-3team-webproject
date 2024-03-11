package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.DTO.QuestionDTO;

public interface QuestionService {
    public List<QuestionDTO> findAllByQuestionTo(String questionTo);
    public void deleteByQuestionId(Long questionId);
}
