package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping
    public ApiResponse<MissionResDTO.MissionListResDTO> getMissions() {

        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                missionService.getMissions()
        );
    }

    // 미션 성공 누르기
    @PatchMapping("/{missionId}/complete")
    public ResponseEntity<Void> completeMission(@PathVariable Long missionId) {
        missionService.completeMission(missionId);
        return ResponseEntity.ok().build();
    }

    // 내가 진행중인 미션 조회
    @PostMapping("/ongoing")
    public ApiResponse<MissionResDTO.MissionListResDTO> getOngoingMissions(
            @RequestBody MissionReqDTO.OngoingMissionReqDTO req
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                missionService.getOngoingMissions(req)
        );
    }
}