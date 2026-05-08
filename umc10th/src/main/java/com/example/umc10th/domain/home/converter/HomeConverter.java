package com.example.umc10th.domain.home.converter;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {
    public static HomeResDTO.HomeMissionListDto toHomeMissionListDto(Page<Mission> missionPage) {
        List<HomeResDTO.HomeMissionDto> missionDtoList = missionPage.getContent().stream()
                .map(HomeConverter::toHomeMissionDto)
                .collect(Collectors.toList());

        return HomeResDTO.HomeMissionListDto.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDtoList.size())
                .missionList(missionDtoList)
                .build();
    }

    public static HomeResDTO.HomeMissionDto toHomeMissionDto(Mission mission) {
        return HomeResDTO.HomeMissionDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getLocation().getName())
                .rewardPoint(mission.getPoint())
                .conditional(mission.getConditional())
                .category("미지정")
                .dDay((int) ChronoUnit.DAYS.between(LocalDateTime.now(), mission.getDeadline()))
                .build();
    }
}