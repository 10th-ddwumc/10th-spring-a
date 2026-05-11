package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public class ReviewRequestDTO {
    public record ReviewCreateDto(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId,

            @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
            @Size(min = 5, message = "리뷰는 최소 5자 이상 작성해주세요.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
            BigDecimal star,

            List<String> photoUrls
    ) {}

    public record GetMyReviewsDto(
            Long memberId,
            Long lastReviewId,
            BigDecimal lastRating,
            Integer size
    ) {}
}
