package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.CreateDto createReview(ReviewRequestDTO.ReviewCreateDto request) {
        Member member = memberRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        Review review = reviewRepository.save(Review.builder()
                .content(request.content())
                .star(request.star())
                .member(member)
                .build());

        return ReviewResponseDTO.CreateDto.builder()
                .reviewId(review.getId())
                .storeId(request.storeId())
                .userId(member.getId())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    public ReviewResponseDTO.GetReviewListDto getMyReviews(ReviewRequestDTO.GetMyReviewsDto request, String sort) {
        int pageSize = (request.size() == null || request.size() <= 0) ? 10 : request.size();
        Long lastId = (request.lastReviewId() == null || request.lastReviewId() <= 0) ? Long.MAX_VALUE : request.lastReviewId();

        Slice<Review> reviewSlice;

        if ("rating".equalsIgnoreCase(sort)) {
            BigDecimal lastRating = (request.lastRating() == null || request.lastRating().compareTo(BigDecimal.ZERO) <= 0)
                    ? new BigDecimal("10.0") : request.lastRating();
            reviewSlice = reviewRepository.findMyReviewsByRatingCursor(request.memberId(), lastRating, lastId, PageRequest.of(0, pageSize));
        } else {
            reviewSlice = reviewRepository.findMyReviewsByIdCursor(request.memberId(), lastId, PageRequest.of(0, pageSize));
        }

        if (reviewSlice.isEmpty()) {
            return ReviewConverter.toGetReviewListDto(reviewSlice, null, null);
        }

        Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
        Long nextId = reviewSlice.hasNext() ? lastReview.getId() : null;
        BigDecimal nextRating = reviewSlice.hasNext() ? lastReview.getStar() : null;

        return ReviewConverter.toGetReviewListDto(reviewSlice, nextId, nextRating);
    }
}