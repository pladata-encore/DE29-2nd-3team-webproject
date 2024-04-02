package com.example.askproject.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionTypes {
    UserException("잘못된 요청입니다. 첫 페이지로 이동합니다."),
    PageException("오류가 발생했습니다. 첫 페이지로 이동합니다."),
    NoHandlerFoundException("요청하신 페이지를 찾을 수 없습니다."),
    NoResourceFoundException("잘못된 요청입니다.");
    private String msg;
}
