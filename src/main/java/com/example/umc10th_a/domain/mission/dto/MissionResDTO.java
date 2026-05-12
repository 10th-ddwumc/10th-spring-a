package com.example.umc10th_a.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class GetMission {
        private Long missionId;
        private Integer point;
        private String conditional;
    }

    @Getter
    @Builder
    public static class Pagination<T> {
        private List<T> data;
        private Integer pageNumber;
        private Integer pageSize;
    }

    @Getter
    @Builder
    public static class CursorPagination<T> {
        private List<T> data;
        private Boolean hasNext;
        private String nextCursor;
        private Integer size;
    }
}