package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/me/missions")
    public ApiResponse<MissionResDTO.MissionListDto> getMyMissions(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(name = "status", defaultValue = "CHALLENGING") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getMyMissions(memberId, status, page));
    }

    @PostMapping("/my/progress")
    public ApiResponse<MissionResDTO.MissionListDto> getMyMissionsByBody(
            @RequestBody @Valid MissionReqDTO.MyMissionListDto request) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getMyMissions(request.memberId(), "CHALLENGING", request.page()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.onFailure(MissionErrorCode.MISSION_NOT_FOUND, errorMessage);
    }

    @ExceptionHandler(ProjectException.class)
    public ApiResponse<Object> handleProjectException(ProjectException e) {
        return ApiResponse.onFailure(e.getErrorCode(), null);
    }
}