package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionRequestDTO {

    public record MissionCreateDto(
            Long storeId,
            LocalDate deadline,
            String conditional,
            Integer point
    ) {}

    public record GetMyMissionsDto(
            Long memberId,
            String status,
            Integer page,
            Integer size
    ) {}
}