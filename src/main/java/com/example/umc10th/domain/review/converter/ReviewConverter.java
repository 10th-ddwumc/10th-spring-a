package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewDto toReviewDto(Review review) {
        return ReviewResponseDTO.ReviewDto.builder()
                .reviewId(review.getId())
                .storeName(review.getStore() != null ? review.getStore().getName() : "가게 정보 없음")
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : LocalDate.now())
                .build();
    }

    public static ReviewResponseDTO.GetReviewListDto toGetReviewListDto(
            Slice<Review> reviewSlice,
            Long nextCursorId,
            BigDecimal nextCursorRating
    ) {
        List<ReviewResponseDTO.ReviewDto> reviewDtoList = reviewSlice.getContent().stream()
                .map(ReviewConverter::toReviewDto)
                .collect(Collectors.toList());

        return ReviewResponseDTO.GetReviewListDto.builder()
                .reviewList(reviewDtoList)
                .nextCursorId(nextCursorId)
                .nextCursorRating(nextCursorRating)
                .hasNext(reviewSlice.hasNext())
                .build();
    }
}