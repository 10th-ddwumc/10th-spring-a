package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.time.LocalDateTime;

public class ReviewConverter {
    public static Review toEntity(ReviewReqDTO.ReviewCreateReqDTO request, Store store, Member member) {
        return Review.builder()
                .store(store)
                .user(member)
                .star(request.star()) // record는 메서드 형태로 호출합니다.
                .content(request.content())
                .pictureUrl(request.pictureUrl())
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewCreateResDTO(Review review, Long missionId) {
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