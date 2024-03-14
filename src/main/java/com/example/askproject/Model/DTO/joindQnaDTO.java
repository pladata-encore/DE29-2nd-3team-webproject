package com.example.askproject.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class joindQnaDTO {
    private Long questionId;
    private String questionFrom;
    private String questionTo;
    private String questionContent;
    private boolean answered;
    private boolean anonymous;
    private Long answerId;
    private String answerFrom;
    private String answerTo;
    private String answerContent;
    private Long answerQuestionId;
    
}
