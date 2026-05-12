package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{missionId}/review")
    public ApiResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable Long missionId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateReqDTO request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                reviewService.createReview(missionId, request)
        );
    }

    @PostMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.MyReviewListResDTO> getMyReviews(
            @RequestBody ReviewReqDTO.MyReviewListReqDTO request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                reviewService.getMyReviews(request)
        );
    }
}
