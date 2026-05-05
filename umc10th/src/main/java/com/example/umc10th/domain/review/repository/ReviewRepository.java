package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // JpaRepository를 상속받으면 save(), findById() 등을 자동으로 사용할 수 있습니다.[cite: 1, 6]
}