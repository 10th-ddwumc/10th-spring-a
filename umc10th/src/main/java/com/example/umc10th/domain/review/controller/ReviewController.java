package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{missionId}")
    public ApiResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable("missionId") Long missionId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateReqDTO request) {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, reviewService.createReview(missionId, request));
    }

    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewListDto> getMyReviews(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(name = "lastStar", required = false) Float lastStar,
            @RequestParam(name = "lastReviewId", required = false) Long lastReviewId,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ApiResponse.onSuccess(ReviewSuccessCode.OK,
                ReviewConverter.toReviewListDto(reviewService.getMyReviews(memberId, lastStar, lastReviewId, size)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        // 수정: ReviewErrorCode 사용 및 인자 2개 규격 준수
        return ApiResponse.onFailure(ReviewErrorCode.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(ProjectException.class)
    public ApiResponse<Object> handleProjectException(ProjectException e) {
        return ApiResponse.onFailure(e.getErrorCode(), null);
    }
}