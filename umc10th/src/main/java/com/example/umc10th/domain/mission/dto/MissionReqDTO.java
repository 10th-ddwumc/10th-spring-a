package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {
    public record CreateMissionDto(
            Integer reward,
            String deadline,
            String missionSpec
    ) {}

    public record ChallengeMissionDto(
            Long memberId
    ) {}

    public record CreateStoreMissionDto(
            LocalDate deadline,
            Integer point,
            String conditional
    ) {}
    public record CreateMission(
            @NotNull LocalDate deadline,
            @NotNull @Min(0) Integer point,
            @NotBlank String conditional
    ) {}
    public record MyMissionListDto(
            @NotNull Long memberId,
            @NotNull Integer page
    ) {}
}