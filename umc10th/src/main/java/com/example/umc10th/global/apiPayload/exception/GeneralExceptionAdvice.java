package com.example.umc10th.global.apiPayload.exception;

import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        // 인자 2개 규격 준수: (BaseErrorCode, T data)
        return ApiResponse.onFailure(MissionErrorCode.MISSION_NOT_FOUND, errorMessage);
    }

    @ExceptionHandler(ProjectException.class)
    public ApiResponse<Object> handleProjectException(ProjectException e) {
        return ApiResponse.onFailure(e.getErrorCode(), null);
    }
}