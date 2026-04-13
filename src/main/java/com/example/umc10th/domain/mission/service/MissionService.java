package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;

public interface MissionService {
    MissionResponseDTO.CreateDto createMission(MissionRequestDTO.CreateDto request);
    MissionResponseDTO.ParticipateDto participateMission(Long missionId, Long memberId);
}