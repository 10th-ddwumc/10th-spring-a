package com.example.umc10th_a.domain.member.service;

import com.example.umc10th_a.domain.member.dto.MemberReqDTO;
import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.entity.Member;
import com.example.umc10th_a.domain.member.repository.MemberRepository;
import com.example.umc10th_a.global.security.entity.AuthMember;
import com.example.umc10th_a.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.Join join(MemberReqDTO.Join request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())

                // 기존 Member 엔티티에서 nullable = false인 값들 임시 기본값 처리
                .birth(LocalDate.now())
                .detailAddress("")
                .socialUid(request.getEmail())

                .build();

        memberRepository.save(member);

        return new MemberResDTO.Join(member.getId());
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        return new MemberResDTO.Login(accessToken);
    }
}