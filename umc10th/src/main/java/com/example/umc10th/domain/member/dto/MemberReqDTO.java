package com.example.umc10th.domain.member.dto;

import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

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
    public record JoinDto(
            String email,
            String name,
            String phoneNumber,
            String gender,
            String address
    ) {}
}