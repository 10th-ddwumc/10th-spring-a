package com.example.umc10th_a.domain.mission.exception;

import com.example.umc10th_a.global.exception.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(
            HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 생성했습니다."
    ),

    OK(
            HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 미션을 조회했습니다."
    ),

    MY_MISSION_OK(
            HttpStatus.OK,
            "MISSION200_3",
            "성공적으로 진행 중인 미션을 조회했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}