package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 생성
    @PostMapping
    public ApiResponse<MissionResponseDTO.CreateDto> createMission(
            @RequestBody MissionRequestDTO.CreateDto request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.createMission(request)
        );
    }

    // 미션 참여
    @PostMapping("/{missionId}/members/{memberId}")
    public ApiResponse<MissionResponseDTO.ParticipateDto> participateMission(
            @PathVariable Long missionId,
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.participateMission(missionId, memberId)
        );
    }
}