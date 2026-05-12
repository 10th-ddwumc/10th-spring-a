package com.example.umc10th_a.domain.member.repository;

import com.example.umc10th_a.domain.member.entity.MemberPreferredFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPreferredFoodRepository extends JpaRepository<MemberPreferredFood, Long> {
}