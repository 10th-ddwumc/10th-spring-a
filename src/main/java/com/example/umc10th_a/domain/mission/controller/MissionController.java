package com.example.umc10th_a.domain.mission.controller;

import com.example.umc10th_a.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_a.domain.mission.dto.MissionResDTO;
import com.example.umc10th_a.domain.mission.exception.MissionSuccessCode;
import com.example.umc10th_a.domain.mission.service.MissionService;
import com.example.umc10th_a.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        missionService.createMission(storeId, dto);

        return ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                null
        );
    }

    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.getMissionsWithPaging(storeId, pageSize, pageNumber, sort)
        );
    }

    @PostMapping("/members/missions/challenging")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMyChallengingMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionRequest request,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MY_MISSION_OK,
                missionService.getMyChallengingMissions(request, pageSize, pageNumber)
        );
    }
}