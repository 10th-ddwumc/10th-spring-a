package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO.CreateDto createReview(ReviewRequestDTO.CreateDto request);
}