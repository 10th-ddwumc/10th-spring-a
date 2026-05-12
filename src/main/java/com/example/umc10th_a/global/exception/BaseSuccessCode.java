package com.example.umc10th_a.global.exception;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();
}