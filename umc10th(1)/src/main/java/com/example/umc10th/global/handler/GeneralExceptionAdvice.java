package com.example.umc10th.global.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // Request Body 검증 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException e
    ) {

        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(
                        ApiResponse.onFailure(
                                GeneralErrorCode.BAD_REQUEST,
                                null
                        )
                );
    }
}