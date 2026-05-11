package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static Review toEntity(ReviewReqDTO.ReviewCreateReqDTO request, Store store, Member member) {
        return Review.builder()
                .store(store)
                .user(member)
                .star(request.star())
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

    public static ReviewResDTO.ReviewListDto toReviewListDto(Slice<Review> reviewSlice) {
        List<ReviewResDTO.ReviewDto> reviewDtoList = reviewSlice.getContent().stream()
                .map(review -> ReviewResDTO.ReviewDto.builder()
                        .reviewId(review.getReviewId())
                        .storeName(review.getStore().getLocation().getName())
                        .star(review.getStar())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDto.builder()
                .reviewList(reviewDtoList)
                .listSize(reviewDtoList.size())
                .hasNext(reviewSlice.hasNext())
                .build();
    }
}