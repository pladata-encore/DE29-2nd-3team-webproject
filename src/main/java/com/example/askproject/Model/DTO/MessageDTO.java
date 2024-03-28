package com.example.askproject.Model.DTO;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDTO {

    private String message;              // 사용자에게 전달할 메시지
    private String redirectUri;          // 리다이렉트 URI
    private RequestMethod method;        // HTTP 요청 메서드
}
