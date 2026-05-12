package com.example.umc10th.domain.mission.dto;

import lombok.Getter;

public class MissionReqDTO {

    // 미션 목록 조회
    @Getter
    public static class MissionListReqDTO {
        private int page;
        private int size;
    }

    // 내가 진행중인 미션 조회
    @Getter
    public static class OngoingMissionReqDTO {
        private Long memberId;
        private int page;
        private int size;
    }
}