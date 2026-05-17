package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    @Builder
    @Getter
    public static class JoinResultDTO {
        private Long memberId;
        private LocalDateTime createdAt;
    }
}
