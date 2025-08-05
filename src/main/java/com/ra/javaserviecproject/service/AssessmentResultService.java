package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.AssessmentResultDTO;
import com.ra.javaserviecproject.model.entity.AssessmentResult;

import java.util.List;

public interface AssessmentResultService {
    List<AssessmentResult> findAll();
    AssessmentResult findById(Integer id);
    AssessmentResult add(AssessmentResultDTO mentor);
    AssessmentResult update(AssessmentResultDTO mentor, Integer id);
    void delete(Integer id);
}
