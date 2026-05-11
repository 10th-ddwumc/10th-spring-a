package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ReviewResponseDTO.CreateDto createReview(ReviewRequestDTO.CreateDto request);
}