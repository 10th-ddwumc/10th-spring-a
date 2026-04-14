package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{missionId}/review")
    public  ApiResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO request) {

        BaseSuccessCode code = MemberSuccessCode.OK;
        return  ApiResponse.onSuccess(code, reviewService.createReview(missionId, request));
    }
}
