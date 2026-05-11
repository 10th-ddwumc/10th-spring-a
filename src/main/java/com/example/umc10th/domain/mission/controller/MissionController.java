package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션 관련 API", description = "미션 생성, 참여, 조회 및 성공 처리를 위한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 생성
    @Operation(summary = "새로운 미션 생성", description = "가게 ID와 마감 기한 등을 입력받아 새로운 미션을 생성합니다.")
    @PostMapping
    public ApiResponse<MissionResponseDTO.CreateDto> createMission(
            @RequestBody MissionRequestDTO.MissionCreateDto request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.createMission(request)
        );
    }

    // 미션 참여
    @Operation(summary = "미션 참여", description = "사용자가 특정 미션에 참여합니다.")
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

    // 미션 목록 조회
    @Operation(summary = "나의 미션 목록 조회", description = "진행 중(CHALLENGING) 혹은 완료(COMPLETE)된 나의 미션 목록을 페이징하여 조회합니다.")
    @PostMapping("/me")
    public ApiResponse<MissionResponseDTO.GetMissionListDto> getMyMissions(
            @RequestBody MissionRequestDTO.GetMyMissionsDto request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.getMyMissions(request)
        );
    }

    // 미션 성공
    @Operation(summary = "미션 성공 처리", description = "도전 중인 미션을 완료 상태로 변경합니다.")
    @PatchMapping("/me/missions/{userMissionId}/complete")
    public ApiResponse<MissionResponseDTO.CompleteDto> completeMission(
            @PathVariable Long userMissionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.completeMission(userMissionId)
        );
    }
}