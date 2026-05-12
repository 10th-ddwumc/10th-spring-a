package com.example.umc10th_a.domain.mission.repository;

import com.example.umc10th_a.domain.mission.entity.MemberMission;
import com.example.umc10th_a.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(
            value = """
                    SELECT mm
                    FROM MemberMission mm
                    JOIN FETCH mm.mission m
                    JOIN FETCH m.store s
                    WHERE mm.member.id = :memberId
                    AND mm.status = :status
                    """,
            countQuery = """
                    SELECT COUNT(mm)
                    FROM MemberMission mm
                    WHERE mm.member.id = :memberId
                    AND mm.status = :status
                    """
    )
    Page<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}