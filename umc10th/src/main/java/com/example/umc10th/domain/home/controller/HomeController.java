package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<HomeResDTO.HomeMissionListDto> getHomeMissions(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(name = "locationId") Long locationId,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, missionService.getHomeMissions(locationId, memberId, page));
    }
}