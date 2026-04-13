package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionResponseDTO {
    @Builder
    public record CreateDto(
            Long missionId,
            Long storeId,
            LocalDate deadline,
            String conditional,
            Integer point,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ParticipateDto(
            Long missionId,
            Long memberId,
            Boolean isComplete
    ) {}
}
