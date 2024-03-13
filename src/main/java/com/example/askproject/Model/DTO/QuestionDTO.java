package com.example.askproject.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDTO {
    private Long questionId;
    private String questionFrom;
    private String questionTo;
    private String questionContent;
    private boolean answered;
    private boolean anonymous;
}
