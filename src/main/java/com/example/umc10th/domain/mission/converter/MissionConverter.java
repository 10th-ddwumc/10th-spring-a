package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionDto toMissionDto(MemberMission memberMission) {
        return MissionResponseDTO.MissionDto.builder()
                .userMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .conditional(memberMission.getMission().getConditional())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .isComplete(memberMission.getIsComplete())
                .build();
    }

    public static MissionResponseDTO.GetMissionListDto toGetMissionListDto(Page<MemberMission> missionPage) {

        List<MissionResponseDTO.MissionDto> missionDtoList = missionPage.stream()
                .map(MissionConverter::toMissionDto)
                .collect(Collectors.toList());

        return MissionResponseDTO.GetMissionListDto.builder()
                .missionList(missionDtoList)
                .listSize(missionDtoList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}