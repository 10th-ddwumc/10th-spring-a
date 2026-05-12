package com.example.umc10th_a.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class RequestBody {
        private String stringTest;
        private Long longTest;
    }

    @Getter
    public static class MyPageRequestDTO {
        private Long id;
    }
}