package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Getter
    @Builder
    public static class MyReviewItemDTO {

        private Long reviewId;
        private Float star;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class MyReviewListResDTO {

        private List<MyReviewItemDTO> reviews;

        // 다음 페이지용 커서
        private Long nextCursorId;
        private Float nextCursorStar;
        private Boolean hasNext;
    }
}
