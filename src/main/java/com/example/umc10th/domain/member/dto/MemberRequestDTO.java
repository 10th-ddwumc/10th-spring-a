package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberRequestDTO {
    // 마이페이지
    public record GetInfo(
            Long id
    ){}

    // 회원가입
    public record JoinDto(
            String name,
            String email,
            String password,
            String phone,
            String address
    ) {}

    // 로그인
    public record LoginDto(
            @NotBlank String email,
            @NotBlank String password
    ) {}
}
