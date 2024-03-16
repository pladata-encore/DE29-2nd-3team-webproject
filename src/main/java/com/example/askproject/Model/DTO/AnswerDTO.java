package com.example.askproject.Model.DTO;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "답변 내용이 없습니다.")
    private String answerContent;
    private Long answerQuestionId;
}
