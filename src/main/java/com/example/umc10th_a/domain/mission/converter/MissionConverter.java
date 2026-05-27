package com.example.umc10th_a.domain.mission.converter;

import com.example.umc10th_a.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_a.domain.mission.dto.MissionResDTO;
import com.example.umc10th_a.domain.mission.entity.Mission;
import com.example.umc10th_a.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .deadline(dto.getDeadline())
                .point(dto.getPoint())
                .conditional(dto.getConditional())
                .build();
    }

    public static MissionResDTO.GetMission toGetMission(Mission mission) {
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .build();
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static <T> MissionResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer size
    ) {
        return MissionResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .size(size)
                .build();
    }
}