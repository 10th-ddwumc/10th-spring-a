package com.example.umc10th_a.domain.member.exception;


import com.example.umc10th_a.global.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 회원을 찾을 수 없습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}