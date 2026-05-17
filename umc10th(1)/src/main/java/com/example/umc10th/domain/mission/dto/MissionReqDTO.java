package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionReqDTO {

    // 미션 목록 조회
    @Getter
    public static class MissionListReqDTO {

        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer page;

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private Integer size;
    }

    // 내가 진행중인 미션 조회
    @Getter
    public static class OngoingMissionReqDTO {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer page;

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private Integer size;
    }
}