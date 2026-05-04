package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface MissionService {
    MissionResponseDTO.CreateDto createMission(MissionRequestDTO.CreateDto request);
    MissionResponseDTO.ParticipateDto participateMission(Long missionId, Long memberId);
    MissionResponseDTO.GetMissionListDto getMyMissions();
    MissionResponseDTO.CompleteDto completeMission(Long userMissionId);
}