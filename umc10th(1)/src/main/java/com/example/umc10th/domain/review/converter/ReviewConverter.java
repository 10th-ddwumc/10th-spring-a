package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.time.LocalDateTime;

public class ReviewConverter {

    // 요청 → Entity
    public static Review toEntity(
            ReviewReqDTO.ReviewCreateReqDTO request,
            Store store,
            Member user
    ) {
        return Review.builder()
                .store(store)
                .user(user)
                .star(request.getStar())
                .content(request.getContent())
                .pictureUrl(request.getPictureUrl())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Entity → 응답 DTO (missionId는 외부에서 받음)
    public static ReviewResDTO.ReviewCreateResDTO toReviewCreateResDTO(
            Review review,
            Long missionId
    ) {
        return ReviewResDTO.ReviewCreateResDTO.builder()
                .reviewId(review.getReviewId())
                .missionId(missionId)
                .star(review.getStar())
                .content(review.getContent())
                .pictureUrl(review.getPictureUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }
}