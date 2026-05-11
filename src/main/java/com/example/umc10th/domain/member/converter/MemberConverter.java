package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;

import java.util.List;

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

    public static MemberResponseDTO.HomeDto toHomeDto(Member member, Integer successCount, List<Mission> availableMissions) {
        return MemberResponseDTO.HomeDto.builder()
                .nickname(member.getName())
                .point(member.getPoint())
                .successCount(successCount)
                .availableMissions(availableMissions.size())
                .build();
    }
}
