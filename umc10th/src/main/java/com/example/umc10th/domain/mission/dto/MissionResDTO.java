package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record CreateMissionResultDto(
            Long missionId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ChallengeMissionResultDto(
            Long memberMissionId,
            LocalDateTime createdAt
    ) {}
    @Builder
    public record MissionListDto(
            List<MissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionDto(
            Long missionId,
            String storeName,
            Integer rewardPoint,
            String conditional,
            String status // 진행중, 성공 등
    ) {}

    @Builder
    public record CompleteMissionResultDto(Long memberMissionId, LocalDateTime updatedAt) {}
}