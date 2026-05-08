package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getHomeMissions(locationId, memberId, page));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        // 에러 해결: (BaseErrorCode, T) 2개 인자만 사용
        return ApiResponse.onFailure(MissionErrorCode.MISSION_NOT_FOUND, errorMessage);
    }

    @ExceptionHandler(ProjectException.class)
    public ApiResponse<Object> handleProjectException(ProjectException e) {
        return ApiResponse.onFailure(e.getErrorCode(), null);
    }
}