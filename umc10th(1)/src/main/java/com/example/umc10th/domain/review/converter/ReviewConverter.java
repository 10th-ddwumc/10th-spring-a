package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

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

    public static ReviewResDTO.MyReviewItemDTO toMyReviewItemDTO(Review review) {
        return ReviewResDTO.MyReviewItemDTO.builder()
                .reviewId(review.getReviewId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewListResDTO toMyReviewListDTO(
            List<Review> reviews,
            boolean hasNext
    ) {
        List<Review> resultReviews = hasNext
                ? reviews.subList(0, reviews.size() - 1)
                : reviews;

        Review lastReview = resultReviews.isEmpty()
                ? null
                : resultReviews.get(resultReviews.size() - 1);

        return ReviewResDTO.MyReviewListResDTO.builder()
                .reviews(
                        resultReviews.stream()
                                .map(ReviewConverter::toMyReviewItemDTO)
                                .toList()
                )
                .nextCursorId(lastReview == null ? null : lastReview.getReviewId())
                .nextCursorStar(lastReview == null ? null : lastReview.getStar())
                .hasNext(hasNext)
                .build();
    }
}