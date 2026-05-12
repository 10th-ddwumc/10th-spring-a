package com.example.umc10th_a.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(
            HttpStatus.OK,
            "COMMON200",
            "성공입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}