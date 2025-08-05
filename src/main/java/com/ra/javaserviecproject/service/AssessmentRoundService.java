package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.AssessmentRoundDTO;
import com.ra.javaserviecproject.model.entity.AssessmentRound;

import java.util.List;

public interface AssessmentRoundService{
    List<AssessmentRound> findAll();
    AssessmentRound findById(Integer id);
    AssessmentRound add(AssessmentRoundDTO student);
    AssessmentRound update(AssessmentRoundDTO student, Integer id);
    void delete(Integer id);
    List<AssessmentRound> findAllByPhaseId(Integer phaseId);
}
