package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.InternshipPhaseDTO;
import com.ra.javaserviecproject.model.entity.InternshipPhase;

import java.util.List;

public interface InternshipPhaseService {
    List<InternshipPhase> findAll();
    InternshipPhase findById(Integer id);
    InternshipPhase add(InternshipPhaseDTO mentor);
    InternshipPhase update(InternshipPhaseDTO mentor, Integer id);
    void delete(Integer id);
}
