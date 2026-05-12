package com.example.umc10th_a.global.handler;

import com.example.umc10th_a.global.apiPayload.ApiResponse;
import com.example.umc10th_a.global.exception.BaseErrorCode;
import com.example.umc10th_a.global.exception.GeneralErrorCode;
import com.example.umc10th_a.global.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(ProjectException ex) {
        BaseErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, ex.getMessage()));
    }
}