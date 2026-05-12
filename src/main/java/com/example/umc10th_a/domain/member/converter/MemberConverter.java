package com.example.umc10th_a.domain.member.converter;

import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.RequestBody toRequestBody(String stringTest, Long longTest) {
        return MemberResDTO.RequestBody.builder()
                .stringTest(stringTest)
                .longTest(longTest)
                .build();
    }

    public static MemberResDTO.MyPageResponseDTO toMyPageResponseDTO(Member member) {
        return MemberResDTO.MyPageResponseDTO.builder()
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}