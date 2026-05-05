package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/my")
    public ApiResponse<MissionResDTO.MissionListDto> getMyMissions(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, missionService.getMyMissions(memberId, status, page));
    }

    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.CompleteMissionResultDto> completeMission(
            @PathVariable("missionId") Long missionId) {
        missionService.completeMission(missionId);
        return ApiResponse.onSuccess(MemberSuccessCode.OK, null);
    }
}