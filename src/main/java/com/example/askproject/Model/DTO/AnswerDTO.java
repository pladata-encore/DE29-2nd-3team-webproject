package com.example.askproject.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long answerId;
    private String answerFrom;
    private String answerTo;
    private String answerContent;
    private Long answerQuestionId;
}
