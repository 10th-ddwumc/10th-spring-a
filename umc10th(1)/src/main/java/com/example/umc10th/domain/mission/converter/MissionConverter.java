package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItemResDTO toMissionItemDTO(Mission mission) {
        return MissionResDTO.MissionItemResDTO.builder()
                .missionId(mission.getId())
                .title(mission.getConditional()) // 👉 제목 없어서 conditional로 대체
                .reward(mission.getPoint())
                .status(mission.getState())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionListResDTO toMissionListDTO(List<Mission> missions) {

        return MissionResDTO.MissionListResDTO.builder()
                .missions(
                        missions.stream()
                                .map(MissionConverter::toMissionItemDTO)
                                .toList()
                )
                .totalCount(missions.size())
                .build();
    }

    public static MissionResDTO.MissionListResDTO toMissionListDTO(Page<Mission> page) {

        return MissionResDTO.MissionListResDTO.builder()
                .missions(
                        page.getContent().stream()
                                .map(MissionConverter::toMissionItemDTO)
                                .toList()
                )
                .totalCount((int) page.getTotalElements())
                .build();
    }

    public static MissionResDTO.MissionCompleteResDTO toMissionCompleteDTO(Mission mission) {
        return MissionResDTO.MissionCompleteResDTO.builder()
                .missionId(mission.getId())
                .status("COMPLETE")
                .completedAt(LocalDateTime.now())
                .build();
    }
}