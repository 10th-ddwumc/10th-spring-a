package com.example.umc10th_a.domain.mission.service;

import com.example.umc10th_a.domain.mission.converter.MissionConverter;
import com.example.umc10th_a.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_a.domain.mission.dto.MissionResDTO;
import com.example.umc10th_a.domain.mission.entity.MemberMission;
import com.example.umc10th_a.domain.mission.entity.Mission;
import com.example.umc10th_a.domain.mission.enums.MissionStatus;
import com.example.umc10th_a.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th_a.domain.mission.repository.MissionRepository;
import com.example.umc10th_a.domain.store.entity.Store;
import com.example.umc10th_a.domain.store.exception.StoreErrorCode;
import com.example.umc10th_a.domain.store.exception.StoreException;
import com.example.umc10th_a.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
    }

    @Transactional(readOnly = true)
    public List<MissionResDTO.GetMission> getMissions(Long storeId) {
        List<Mission> missionList = missionRepository.findAllByStore_Id(storeId);

        return missionList.stream()
                .map(MissionConverter::toGetMission)
                .toList();
    }

    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissionsWithPaging(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo;

        if (sort != null) {
            sortInfo = Sort.by(sort).descending();
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionPage = missionRepository.findAllByStore_Id(storeId, pageRequest);

        List<MissionResDTO.GetMission> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toGetMission)
                .toList();

        return MissionConverter.toPagination(
                missionList,
                missionPage.getNumber(),
                missionPage.getSize()
        );
    }

    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMyChallengingMissions(
            MissionReqDTO.MyMissionRequest request,
            Integer pageSize,
            Integer pageNumber
    ) {
        Sort sortInfo = Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findMyMissionsByMemberIdAndStatus(
                        request.getMemberId(),
                        MissionStatus.CHALLENGING,
                        pageRequest
                );

        List<MissionResDTO.GetMission> missionList = memberMissionPage.getContent().stream()
                .map(memberMission -> MissionConverter.toGetMission(memberMission.getMission()))
                .toList();

        return MissionConverter.toPagination(
                missionList,
                memberMissionPage.getNumber(),
                memberMissionPage.getSize()
        );
    }
}