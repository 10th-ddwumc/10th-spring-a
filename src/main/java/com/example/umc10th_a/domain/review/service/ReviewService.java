package com.example.umc10th_a.domain.review.service;

import com.example.umc10th_a.domain.member.entity.Member;
import com.example.umc10th_a.domain.member.repository.MemberRepository;
import com.example.umc10th_a.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_a.domain.review.dto.ReviewResDTO;
import com.example.umc10th_a.domain.review.entity.Review;
import com.example.umc10th_a.domain.review.repository.ReviewRepository;
import com.example.umc10th_a.domain.store.entity.Store;
import com.example.umc10th_a.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewResultDTO createReview(ReviewReqDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .score(request.getScore())
                .body(request.getBody())
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(savedReview.getId())
                .build();
    }
}