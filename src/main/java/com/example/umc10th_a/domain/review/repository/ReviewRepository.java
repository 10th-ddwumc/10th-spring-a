package com.example.umc10th_a.domain.review.repository;

import com.example.umc10th_a.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}