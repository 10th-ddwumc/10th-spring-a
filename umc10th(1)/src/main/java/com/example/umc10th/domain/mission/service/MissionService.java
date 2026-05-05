package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.member.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 미션 목록 조회
    public MissionResDTO.MissionListResDTO getMissions() {

        List<Mission> missions = missionRepository.findAll();

        return MissionConverter.toMissionListDTO(missions);
    }

    // 미션 완료 처리
    @Transactional
    public void completeMission(Long memberMissionId) {

        MemberMission mm = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        mm.complete();
    }

    // 페이징
    public MissionResDTO.MissionListResDTO getMissions(
            MissionReqDTO.MissionListReqDTO req
    ) {

        // Pageable 생성 (page는 0부터라 보정)
        Pageable pageable = PageRequest.of(
                req.getPage() - 1,
                req.getSize(),
                Sort.by("id").descending()
        );

        Page<Mission> missionPage = missionRepository.findMissions(pageable);
        return MissionConverter.toMissionListDTO(missionPage);
    }
}
