package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회 응답
    @Getter
    @Builder
    public static class MissionListResDTO {
        private List<MissionItemResDTO> missions;
        private int totalCount;
    }

    // 미션 단일 항목
    @Getter
    @Builder
    public static class MissionItemResDTO {
        private Long missionId;
        private String title;
        private Integer reward;       // 포인트
        private String status;        // 미션 상태
        private LocalDateTime deadline; // 마감일
    }

    // 미션 성공 누르기 응답
    @Getter
    @Builder
    public static class MissionCompleteResDTO {
        private Long missionId;
        private String status;        // "COMPLETE"
        private LocalDateTime completedAt;
    }
}