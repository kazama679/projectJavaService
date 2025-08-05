package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.InternshipPhase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipPhaseRepository extends JpaRepository<InternshipPhase, Integer> {
    boolean existsByPhaseNameIgnoreCase(String phaseName);
    boolean existsByPhaseNameAndPhaseIdNot(String phaseName, Integer phaseId);
}