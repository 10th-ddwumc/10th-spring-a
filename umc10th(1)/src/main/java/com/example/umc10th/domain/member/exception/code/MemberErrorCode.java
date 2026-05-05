package com.example.umc10th.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

    // 기본 조회
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_001", "사용자를 찾을 수 없습니다."),

    // 인증/권한
    MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "MEMBER_002", "인증되지 않은 사용자입니다."),
    MEMBER_FORBIDDEN(HttpStatus.FORBIDDEN, "MEMBER_003", "접근 권한이 없습니다."),

    // 중복
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER_004", "이미 존재하는 사용자입니다."),

    // 입력값 관련
    INVALID_MEMBER_REQUEST(HttpStatus.BAD_REQUEST, "MEMBER_005", "잘못된 사용자 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}