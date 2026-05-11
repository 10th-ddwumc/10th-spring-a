package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {
    @Builder
    public record CreateDto(
            Long reviewId,
            Long storeId,
            Long userId,
            String content,
            BigDecimal star,
            List<String> photoUrls,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record GetReviewListDto(
            List<ReviewDto> reviewList,
            Long nextCursorId,
            BigDecimal nextCursorRating,
            Boolean hasNext
    ) {}

    @Builder
    public record ReviewDto(
            Long reviewId,
            String storeName,
            String content,
            BigDecimal star,
            LocalDate createdAt
    ) {}
}
