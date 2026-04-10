package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {
    @Getter
    public static class RequestBody {
        private String stringTest;
        private Long longTest;
    }

    //마이페이지
    public record GetInfo(
            Long id
    ){}
}