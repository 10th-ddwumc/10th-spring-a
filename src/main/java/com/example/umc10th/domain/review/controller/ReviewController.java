package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰 관련 API", description = "가게 리뷰 작성 및 관리를 위한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @Operation(summary = "리뷰 작성 API", description = "가게 ID, 별점, 내용 및 사진 URL 등을 받아 리뷰를 등록합니다.")
    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateDto> createReview(
            @RequestBody ReviewRequestDTO.ReviewCreateDto request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.OK,
                reviewService.createReview(request)
        );
    }
}