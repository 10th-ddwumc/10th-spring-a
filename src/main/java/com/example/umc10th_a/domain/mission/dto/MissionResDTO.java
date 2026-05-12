package com.example.umc10th_a.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionPreviewDTO {
        private Long missionId;
        private String storeName;
        private String missionSpec;
        private Integer reward;
    }
}