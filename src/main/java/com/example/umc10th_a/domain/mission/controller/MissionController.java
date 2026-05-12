package com.example.umc10th_a.domain.mission.controller;

import com.example.umc10th_a.domain.mission.dto.MissionResDTO;
import com.example.umc10th_a.domain.mission.enums.MissionStatus;
import com.example.umc10th_a.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/available")
    public Page<MissionResDTO.MissionPreviewDTO> getAvailableMissions(
            @RequestParam Long regionId,
            @RequestParam Long memberId,
            Pageable pageable
    ) {
        return missionService.getAvailableMissions(regionId, memberId, pageable);
    }

    @GetMapping("/my")
    public Page<MissionResDTO.MissionPreviewDTO> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            Pageable pageable
    ) {
        return missionService.getMyMissions(memberId, status, pageable);
    }
}