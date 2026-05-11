package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline().atStartOfDay())
                .state("ACTIVE")
                .build();
    }

    public static MissionResDTO.MissionListDto toMemberMissionListDto(Page<MemberMission> memberMissionPage) {
        List<MissionResDTO.MissionDto> missionDtoList = memberMissionPage.getContent().stream()
                .map(mm -> toMissionDto(mm.getMission(), mm.getIsComplete() ? "성공" : "진행중"))
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDto.builder()
                .isLast(memberMissionPage.isLast())
                .isFirst(memberMissionPage.isFirst())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .listSize(missionDtoList.size())
                .missionList(missionDtoList)
                .build();
    }

    public static MissionResDTO.MissionListDto toMissionListDto(Page<Mission> missionPage) {
        List<MissionResDTO.MissionDto> missionDtoList = missionPage.getContent().stream()
                .map(m -> toMissionDto(m, m.getState()))
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDto.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDtoList.size())
                .missionList(missionDtoList)
                .build();
    }

    public static MissionResDTO.MissionDto toMissionDto(Mission mission, String status) {
        return MissionResDTO.MissionDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getLocation().getName())
                .rewardPoint(mission.getPoint())
                .conditional(mission.getConditional())
                .status(status)
                .build();
    }
}