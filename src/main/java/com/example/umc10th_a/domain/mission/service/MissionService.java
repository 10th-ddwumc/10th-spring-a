package com.example.umc10th_a.domain.mission.service;

import com.example.umc10th_a.domain.mission.dto.MissionResDTO;
import com.example.umc10th_a.domain.mission.entity.MemberMission;
import com.example.umc10th_a.domain.mission.entity.Mission;
import com.example.umc10th_a.domain.mission.enums.MissionStatus;
import com.example.umc10th_a.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th_a.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Page<MissionResDTO.MissionPreviewDTO> getAvailableMissions(
            Long regionId,
            Long memberId,
            Pageable pageable
    ) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(
                regionId,
                memberId,
                pageable
        );

        return missions.map(mission -> MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .build());
    }

    public Page<MissionResDTO.MissionPreviewDTO> getMyMissions(
            Long memberId,
            MissionStatus status,
            Pageable pageable
    ) {
        Page<MemberMission> memberMissions = memberMissionRepository.findMyMissions(
                memberId,
                status,
                pageable
        );

        return memberMissions.map(memberMission -> {
            Mission mission = memberMission.getMission();

            return MissionResDTO.MissionPreviewDTO.builder()
                    .missionId(mission.getId())
                    .storeName(mission.getStore().getName())
                    .missionSpec(mission.getMissionSpec())
                    .reward(mission.getReward())
                    .build();
        });
    }
}