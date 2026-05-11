package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {
    @Getter
    public static class RequestBody {
    private String stringTest;
    private Long longTest;
    }

    public record GetInfo(
            Long id
    ){}

    public record JoinDto(
            String email,
            String password,
            String name,
            String phoneNumber,
            String gender,
            String address
    ) {}
}