package com.example.umc10th_a.domain.review.controller;

import com.example.umc10th_a.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_a.domain.review.dto.ReviewResDTO;
import com.example.umc10th_a.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewResDTO.CreateReviewResultDTO createReview(
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        return reviewService.createReview(request);
    }
}