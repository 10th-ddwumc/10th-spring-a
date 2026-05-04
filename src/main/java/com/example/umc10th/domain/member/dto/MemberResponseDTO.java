package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    @Builder
    public record JoinDto(
            String name,
            String email,
            String password,
            String phone,
            String address
    ) {}

    @Builder
    public record HomeDto(
            String nickname,
            Integer point
    ) {}
}
