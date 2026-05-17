package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ) {}

    public record JoinDto(
            @NotBlank String name,

            @NotBlank
            @Email
            String email,

            @NotBlank String password
    ) {}
}