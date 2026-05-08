package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final MissionService missionService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<String> createMission(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission request) {
        missionService.createStoreMission(storeId, request);
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, null);
    }

    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionListDto> getStoreMissions(
            @PathVariable("storeId") Long storeId,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getStoreMissions(storeId, page));
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