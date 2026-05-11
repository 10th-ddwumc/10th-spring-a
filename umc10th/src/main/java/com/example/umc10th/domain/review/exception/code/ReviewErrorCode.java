package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_1", "잘못된 리뷰 요청입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
    @Override
    public String getCode() { return this.code; }
    @Override
    public String getMessage() { return this.message; }
    @Override
    public int getStatus() { return this.httpStatus.value(); }
}