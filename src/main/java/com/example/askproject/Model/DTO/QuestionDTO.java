package com.example.askproject.Model.DTO;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "질문 내용이 없습니다.")
    private String questionContent;
    private boolean answered;
    private boolean anonymous;
}
