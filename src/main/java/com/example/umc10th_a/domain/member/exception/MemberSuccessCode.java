package com.example.umc10th_a.domain.member.exception;

import com.example.umc10th_a.global.exception.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    TEST_SUCCESS(
            HttpStatus.OK,
            "MEMBER200_0",
            "테스트 API 호출에 성공했습니다."
    ),

    GET_MY_PAGE(
            HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 유저를 조회했습니다."
    ),

    REQUEST_BODY_SUCCESS(
            HttpStatus.OK,
            "MEMBER200_2",
            "Request Body 테스트에 성공했습니다."
    ),

    QUERY_PARAMETER_SUCCESS(
            HttpStatus.OK,
            "MEMBER200_3",
            "Query Parameter 테스트에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}