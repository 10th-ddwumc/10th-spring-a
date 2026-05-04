package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDTO.GetInfo getInfo(MemberRequestDTO.GetInfo dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));
        return MemberConverter.toGetInfo(member);
    }

    public MemberResponseDTO.JoinDto join(MemberRequestDTO.JoinDto request) {
        return MemberResponseDTO.JoinDto.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .phone(request.phone())
                .address(request.address())
                .build();
    }

    public MemberResponseDTO.HomeDto getHome(MemberRequestDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));

        return MemberResponseDTO.HomeDto.builder()
                .nickname(member.getName())
                .point(member.getPoint())
                .build();
    }
}
