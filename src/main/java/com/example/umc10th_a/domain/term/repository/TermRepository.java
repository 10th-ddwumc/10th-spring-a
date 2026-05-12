package com.example.umc10th_a.domain.term.repository;

import com.example.umc10th_a.domain.term.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}