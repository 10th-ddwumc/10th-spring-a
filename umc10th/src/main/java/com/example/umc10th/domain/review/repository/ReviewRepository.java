package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
        // 별점 내림차순, ID 내림차순 커서 기반 쿼리
        @Query("SELECT r FROM Review r WHERE r.user.id = :memberId AND (r.star < :star OR (r.star = :star AND r.reviewId < :reviewId)) ORDER BY r.star DESC, r.reviewId DESC")
        Slice<Review> findByMemberIdAndStarCursor(@Param("memberId") Long memberId, @Param("star") Float star, @Param("reviewId") Long reviewId, Pageable pageable);

        // 최신순(ID 내림차순) 커서 기반 쿼리
        @Query("SELECT r FROM Review r WHERE r.user.id = :memberId AND r.reviewId < :reviewId ORDER BY r.reviewId DESC")
        Slice<Review> findByMemberIdAndIdCursor(@Param("memberId") Long memberId, @Param("reviewId") Long reviewId, Pageable pageable);

}