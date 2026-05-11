package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 최신 순 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :lastId ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdCursor(Long memberId, Long lastId, Pageable pageable);

    // 별점 순 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND " +
            "(r.star < :lastRating OR (r.star = :lastRating AND r.id < :lastId)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findMyReviewsByRatingCursor(Long memberId, BigDecimal lastRating, Long lastId, Pageable pageable);
}