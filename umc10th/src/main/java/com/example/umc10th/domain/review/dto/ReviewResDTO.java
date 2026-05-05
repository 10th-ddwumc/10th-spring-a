package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;

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
}