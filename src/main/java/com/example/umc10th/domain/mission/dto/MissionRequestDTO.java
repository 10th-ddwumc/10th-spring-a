package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionRequestDTO {

    public record CreateDto(
            Long storeId,
            LocalDate deadline,
            String conditional,
            Integer point
    ) {}
}