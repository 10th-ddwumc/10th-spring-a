package com.example.umc10th_a.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @AllArgsConstructor
    public static class Join {
        private Long memberId;
    }

    @Getter
    @AllArgsConstructor
    public static class Login {
        private String accessToken;
    }

    @Getter
    @AllArgsConstructor
    public static class GetInfo {
        private Long memberId;
        private String email;
        private String name;
    }

    // 기존 MemberConverter에서 사용하던 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    public static class RequestBody {
        private String stringTest;
        private Long longTest;
    }

    // 기존 마이페이지 응답 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPageResponseDTO {
        private String name;
        private String profileUrl;
        private String email;
        private String phoneNumber;
        private Integer point;
    }
}