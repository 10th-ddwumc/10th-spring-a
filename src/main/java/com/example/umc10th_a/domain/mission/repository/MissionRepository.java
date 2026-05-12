package com.example.umc10th_a.domain.mission.repository;

import com.example.umc10th_a.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findAllByStore_Id(Long storeId);

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    @Query(
            value = """
                    SELECT m
                    FROM Mission m
                    JOIN m.store s
                    JOIN s.region r
                    WHERE r.id = :regionId
                    AND m.deadline >= CURRENT_DATE
                    AND NOT EXISTS (
                        SELECT mm
                        FROM MemberMission mm
                        WHERE mm.member.id = :memberId
                        AND mm.mission = m
                    )
                    """,
            countQuery = """
                    SELECT COUNT(m)
                    FROM Mission m
                    JOIN m.store s
                    JOIN s.region r
                    WHERE r.id = :regionId
                    AND m.deadline >= CURRENT_DATE
                    AND NOT EXISTS (
                        SELECT mm
                        FROM MemberMission mm
                        WHERE mm.member.id = :memberId
                        AND mm.mission = m
                    )
                    """
    )
    Page<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}