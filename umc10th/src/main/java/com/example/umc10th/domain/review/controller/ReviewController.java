package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{missionId}")
    public ApiResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable("missionId") Long missionId,
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO request) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, reviewService.createReview(missionId, request));
    }
}