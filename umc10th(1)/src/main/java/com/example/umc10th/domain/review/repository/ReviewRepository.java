package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID순 첫 페이지
    Slice<Review> findByUserIdOrderByReviewIdDesc(
            Long userId,
            Pageable pageable
    );

    // ID순 다음 페이지
    Slice<Review> findByUserIdAndReviewIdLessThanOrderByReviewIdDesc(
            Long userId,
            Long cursorId,
            Pageable pageable
    );

    // 별점순 첫 페이지
    Slice<Review> findByUserIdOrderByStarDescReviewIdDesc(
            Long userId,
            Pageable pageable
    );

    // 별점순 다음 페이지
    @Query("""
            select r from Review r
            where r.user.id = :userId
            and (
                r.star < :cursorStar
                or (r.star = :cursorStar and r.reviewId < :cursorId)
            )
            order by r.star desc, r.reviewId desc
            """)
    Slice<Review> findMyReviewsByStarCursor(
            @Param("userId") Long userId,
            @Param("cursorStar") Float cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}