package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record ReviewCreateResDTO(
            Long reviewId,
            Long missionId,
            Float star,
            String content,
            String pictureUrl,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewListDto(
            List<ReviewDto> reviewList,
            Integer listSize,
            Boolean hasNext
    ) {}

    @Builder
    public record ReviewDto(
            Long reviewId,
            String storeName,
            Float star,
            String content,
            LocalDateTime createdAt
    ) {}
}