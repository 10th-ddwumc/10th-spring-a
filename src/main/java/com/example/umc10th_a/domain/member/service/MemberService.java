package com.example.umc10th_a.domain.member.service;

import com.example.umc10th_a.global.security.entity.AuthMember;
import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    public MemberResDTO.GetInfo getInfo(AuthMember authMember) {
        Member member = authMember.getMember();

        return new MemberResDTO.GetInfo(
                member.getId(),
                member.getEmail(),
                member.getName()
        );
    }
}