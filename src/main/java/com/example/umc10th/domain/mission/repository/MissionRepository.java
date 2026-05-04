package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 - 참여하지 않은 미션 목록
    @Query("SELECT m FROM Mission m " +
            "WHERE m.id NOT IN (" +
            "SELECT mm.mission.id " +
            "FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId" +
            ")")
    Page<Mission> findChallengableMissions(@Param("memberId") Long memberId, Pageable pageable);
}