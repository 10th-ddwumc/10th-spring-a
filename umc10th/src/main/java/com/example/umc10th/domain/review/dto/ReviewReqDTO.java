package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {
    public record ReviewCreateReqDTO(
            Long userId,
            Float star,
            String content,
            String pictureUrl
    ) {}
}