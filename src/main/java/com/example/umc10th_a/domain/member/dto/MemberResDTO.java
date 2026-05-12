package com.example.umc10th_a.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    public static class RequestBody {
        private String stringTest;
        private Long longTest;
    }

    @Getter
    @Builder
    public static class MyPageResponseDTO {
        private String name;
        private String profileUrl;
        private String email;
        private String phoneNumber;
        private Integer point;
    }
}