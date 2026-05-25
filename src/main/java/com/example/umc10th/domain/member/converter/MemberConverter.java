package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.global.security.dto.OAuthDTO;

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

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .password(null)
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    public static MemberResponseDTO.LoginResultDto toLogin(String accessToken) {
        return MemberResponseDTO.LoginResultDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
