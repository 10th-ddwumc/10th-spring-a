package com.example.umc10th_a.domain.region.repository;

import com.example.umc10th_a.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}