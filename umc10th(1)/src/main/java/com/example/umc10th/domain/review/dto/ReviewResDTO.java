package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class ReviewCreateResDTO {
        private Long reviewId;
        private Long missionId;
        private Float star;
        private String content;
        private String pictureUrl;
        private LocalDateTime createdAt;
    }
}
