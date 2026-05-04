package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 미션 생성
    @Override
    @Transactional
    public MissionResponseDTO.CreateDto createMission(MissionRequestDTO.CreateDto request) {
        Mission mission = Mission.builder()
                .deadline(request.deadline())
                .conditional(request.conditional())
                .point(request.point())
                .build();

        Mission savedMission = missionRepository.save(mission);

        return MissionResponseDTO.CreateDto.builder()
                .missionId(savedMission.getId())
                .deadline(savedMission.getDeadline())
                .conditional(savedMission.getConditional())
                .point(savedMission.getPoint())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 미션 참여
    @Override
    @Transactional
    public MissionResponseDTO.ParticipateDto participateMission(Long missionId, Long memberId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();

        MemberMission memberMission = MemberMission.builder()
                .mission(mission)
                .member(member)
                .isComplete(false)
                .build();

        memberMissionRepository.save(memberMission);

        return new MissionResponseDTO.ParticipateDto(missionId, memberId, false);
    }

    // 미션 목록 조회
    @Override
    public MissionResponseDTO.GetMissionListDto getMyMissions() {
        return null;
    }

    // 미션 성공
    @Override
    @Transactional
    public MissionResponseDTO.CompleteDto completeMission(Long userMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new RuntimeException("해당 참여 내역이 없습니다."));

        memberMission.complete();

        return new MissionResponseDTO.CompleteDto(userMissionId, memberMission.getIsComplete());
    }
}