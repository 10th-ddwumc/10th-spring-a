package com.example.umc10th.domain.home.dto;

import lombok.Builder;
import java.util.List;

public class HomeResDTO {
    @Builder
    public record HomeMissionListDto(
            List<HomeMissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record HomeMissionDto(
            Long missionId,
            String storeName,
            String category, // 중식당 등
            String conditional, // 10,000원 이상의 식사 등
            Integer rewardPoint,
            Integer dDay // D-7 등
    ) {}
}