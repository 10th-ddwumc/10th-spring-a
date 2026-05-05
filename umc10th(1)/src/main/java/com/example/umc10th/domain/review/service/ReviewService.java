package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.umc10th.domain.mission.exception.code.MissionErrorCode.MISSION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public ReviewResDTO.ReviewCreateResDTO createReview(
            Long missionId,
            ReviewReqDTO.ReviewCreateReqDTO request
    ) {

        // 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MISSION_NOT_FOUND));

        // Store 가져오기
        Store store = mission.getStore();

        // 유저 조회
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 리뷰 생성
        Review review = ReviewConverter.toEntity(request, store, member);

        // 저장
        reviewRepository.save(review);

        return ReviewConverter.toReviewCreateResDTO(review, missionId);
    }
}
