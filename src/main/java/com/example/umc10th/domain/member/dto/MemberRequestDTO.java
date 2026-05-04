package com.example.umc10th.domain.member.dto;

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
}
