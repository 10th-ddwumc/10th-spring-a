package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public record ParticipateDto(
            Long missionId,
            Long memberId,
            Boolean isComplete
    ) {}

    @Builder
    public record GetMissionListDto(
            List<MissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionDto(
            Long userMissionId,
            Long missionId,
            String storeName,
            String conditional,
            Integer point,
            LocalDate deadline,
            Boolean isComplete
    ) {}

    public record CompleteDto(
            Long userMissionId,
            Boolean isComplete
    ) {}
}