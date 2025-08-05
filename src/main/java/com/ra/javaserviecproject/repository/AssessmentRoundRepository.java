package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.AssessmentRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRoundRepository extends JpaRepository<AssessmentRound, Integer> {
    List<AssessmentRound> findAllByPhase_PhaseId(Integer phaseId);
}