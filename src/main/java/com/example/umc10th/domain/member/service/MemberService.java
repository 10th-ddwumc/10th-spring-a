package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 마이페이지
    public MemberResponseDTO.GetInfo getInfo(MemberRequestDTO.GetInfo dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));
        return MemberConverter.toGetInfo(member);
    }

    // 회원가입
    @Transactional
    public MemberResponseDTO.JoinDto join(MemberRequestDTO.JoinDto request) {
        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = Member.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .phoneNumber(request.phone())
                .address(request.address())
                .build();

        memberRepository.save(member);

        return MemberResponseDTO.JoinDto.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .phone(request.phone())
                .address(request.address())
                .build();
    }

    // 홈 화면 조회
    public MemberResponseDTO.HomeDto getHome(MemberRequestDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));

        // 성공한 미션 개수 조회
        Integer successCount = memberMissionRepository.countByMemberAndStatus(member, MissionStatus.COMPLETE);

        // 사용자의 지역 정보 가져오기
        List<Mission> availableMissions = missionRepository.findAvailableMissionsByLocation(
                member.getLocation(),
                member.getId()
        );

        return MemberConverter.toHomeDto(member, successCount, availableMissions);
    }

    // 로그인
    @Transactional
    public MemberResponseDTO.LoginResultDto login(MemberRequestDTO.LoginDto request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getEmail());

        return MemberResponseDTO.LoginResultDto.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .createdAt(LocalDateTime.now())
                .build();
    }
}