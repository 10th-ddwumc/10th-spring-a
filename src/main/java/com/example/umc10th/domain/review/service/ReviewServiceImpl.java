package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.CreateDto createReview(ReviewRequestDTO.CreateDto request) {

        Member member = memberRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        Review review = Review.builder()
                .content(request.content())
                .star(request.star())
                .member(member)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResponseDTO.CreateDto.builder()
                .reviewId(savedReview.getId())
                .storeId(request.storeId()) // Store 엔티티 미구현으로 요청 데이터 그대로 반환
                .userId(member.getId())
                .content(savedReview.getContent())
                .star(savedReview.getStar())
                .createdAt(LocalDateTime.now())
                .build();
    }
}