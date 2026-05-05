package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 내 미션 목록 조회 (진행중/진행완료)[cite: 5]
    public MissionResDTO.MissionListDto getMyMissions(Long memberId, String status, Integer page) {
        Boolean isComplete = status.equalsIgnoreCase("COMPLETE");
        Page<MemberMission> missions = memberMissionRepository.findAllByMemberIdAndIsComplete(memberId, isComplete, PageRequest.of(page, 10));

        return null; // TODO: Converter 작성 후 연결
    }

    // 미션 완료 처리[cite: 5]
    @Transactional
    public void completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("해당 미션 도전 내역이 없습니다."));
        memberMission.complete();
    }

    // 홈 화면 미션 목록 조회 (지역 기준)[cite: 5]
    public HomeResDTO.HomeMissionListDto getHomeMissions(Long locationId, Long memberId, Integer page) {
        Page<Mission> missions = missionRepository.findChallengableMissions(locationId, memberId, PageRequest.of(page, 10));

        return null; // TODO: Converter 작성 후 연결
    }
}