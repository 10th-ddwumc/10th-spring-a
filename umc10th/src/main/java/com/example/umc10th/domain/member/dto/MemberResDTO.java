package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}
    @Builder
    public record JoinResultDto(Long memberId, LocalDateTime createdAt) {}

    // UI 화면에 맞춘 마이페이지 응답 DTO
    @Builder
    public record MyPageDto(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}
}