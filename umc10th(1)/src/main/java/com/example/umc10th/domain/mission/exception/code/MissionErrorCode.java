package com.example.umc10th.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode {

    // 기본 조회 관련
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_001", "미션을 찾을 수 없습니다."),

    // 상태 관련
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION_002", "이미 완료된 미션입니다."),
    MISSION_NOT_STARTED(HttpStatus.BAD_REQUEST, "MISSION_003", "아직 시작하지 않은 미션입니다."),

    // 시간 관련
    MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION_004", "마감된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}