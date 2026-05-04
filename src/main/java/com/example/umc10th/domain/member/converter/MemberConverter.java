package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResponseDTO.GetInfo toGetInfo(Member member) {
        return MemberResponseDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}
