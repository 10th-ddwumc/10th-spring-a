package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
