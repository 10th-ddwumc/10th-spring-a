package com.example.umc10th_a.domain.food.repository;

import com.example.umc10th_a.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
