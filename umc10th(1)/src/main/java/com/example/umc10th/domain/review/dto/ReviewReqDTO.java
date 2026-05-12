package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateReqDTO {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.5", message = "별점은 0.5점 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0점 이하여야 합니다.")
        private Float star;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;

        private String pictureUrl;
    }

    @Getter
    public static class MyReviewListReqDTO {

        private Long userId;

        private Long cursorId;

        private Float cursorStar;

        private int size;

        private String sort;
    }
}