package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public Slice<Review> getMyReviews(Long memberId, Float lastStar, Long lastReviewId, Integer size) {
        PageRequest pageRequest = PageRequest.of(0, size);

        // 커서(별점) 존재 여부에 따라 리포지토리 메서드 선택
        if (lastStar != null) {
            return reviewRepository.findByMemberIdAndStarCursor(memberId, lastStar, lastReviewId, pageRequest);
        }
        return reviewRepository.findByMemberIdAndIdCursor(memberId, lastReviewId, pageRequest);
    }

    @Transactional
    public ReviewResDTO.ReviewCreateResDTO createReview(Long missionId, ReviewReqDTO.ReviewCreateReqDTO request) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(request.userId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toEntity(request, mission.getStore(), member);
        reviewRepository.save(review);

        return ReviewConverter.toReviewCreateResDTO(review, missionId);
    }
}