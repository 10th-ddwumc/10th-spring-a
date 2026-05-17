package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public Member joinMember(MemberReqDTO.JoinDto dto) {

        if (memberRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        return memberRepository.save(member);
    }

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }
}