package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.home.converter.HomeConverter;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Mission createStoreMission(Long storeId, MissionReqDTO.CreateMission request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, request);
        return missionRepository.save(mission);
    }

    public MissionResDTO.MissionListDto getStoreMissions(Long storeId, Integer page) {
        Page<Mission> missions = missionRepository.findAllByStoreId(storeId, PageRequest.of(page, 10));
        return MissionConverter.toMissionListDto(missions);
    }

    public MissionResDTO.MissionListDto getMyMissions(Long memberId, String status, Integer page) {
        Boolean isComplete = status.equalsIgnoreCase("COMPLETE");
        Page<MemberMission> missions = memberMissionRepository.findAllByMemberIdAndIsComplete(memberId, isComplete, PageRequest.of(page, 10));
        return MissionConverter.toMemberMissionListDto(missions);
    }

    public HomeResDTO.HomeMissionListDto getHomeMissions(Long locationId, Long memberId, Integer page) {
        // 사용
        Page<Mission> missions = missionRepository.findChallengableMissions(locationId, memberId, PageRequest.of(page, 10));
        return HomeConverter.toHomeMissionListDto(missions);
    }
}