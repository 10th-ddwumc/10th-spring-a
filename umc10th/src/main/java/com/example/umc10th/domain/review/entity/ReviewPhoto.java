package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 👈 이 어노테이션이 핵심입니다!
@Table(name = "review_picture")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_picture_id")
    private Long reviewPictureId;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review; // 👈 Review 엔티티와의 양방향 매핑을 위해 필요합니다.

    @OneToOne(mappedBy = "reviewPhoto", cascade = CascadeType.ALL)
    private Reply reply;
}