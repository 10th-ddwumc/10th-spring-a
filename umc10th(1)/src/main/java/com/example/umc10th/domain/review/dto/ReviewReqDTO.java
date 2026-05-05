package com.example.umc10th.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateReqDTO {
        private Long userId;          // 작성자
        private Float star;           // 별점
        private String content;       // 내용
        private String pictureUrl;    // 사진 URL
    }
}
