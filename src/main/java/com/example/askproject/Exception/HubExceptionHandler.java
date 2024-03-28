package com.example.askproject.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HubExceptionHandler {
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Map<String, Object>> UserExceptionHandler(UserException e){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, Object> map = new HashMap<>();
        map.put("exceptionType", "UserException");
        map.put("message", "잘못된 요청입니다. 첫 페이지로 돌아갑니다.");
        return new ResponseEntity<>(map, httpHeaders, httpStatus);
    }

    @ExceptionHandler(value = PageException.class)
    public ResponseEntity<Map<String, Object>> ServiceExceptionHandler(PageException e){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;
        Map<String, Object> map = new HashMap<>();
        map.put("exceptionType", "ServiceException");
        map.put("message", "오류가 발생했습니다. 첫 페이지로 돌아갑니다.");
        return new ResponseEntity<>(map, httpHeaders, httpStatus);
    }
}
