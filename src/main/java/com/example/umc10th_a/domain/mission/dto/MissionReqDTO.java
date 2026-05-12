package com.example.umc10th_a.domain.mission.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionReqDTO {

    @Getter
    public static class CreateMission {
        @NotNull(message = "마감일은 필수입니다.")
        @Future(message = "마감일은 현재 날짜보다 미래여야 합니다.")
        private LocalDate deadline;

        @NotNull(message = "포인트는 필수입니다.")
        @Min(value = 1, message = "포인트는 1 이상이어야 합니다.")
        private Integer point;

        @NotBlank(message = "미션 조건은 필수입니다.")
        private String conditional;
    }

    @Getter
    public static class MyMissionRequest {
        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;
    }
}