package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.RoundCriterionDTO;
import com.ra.javaserviecproject.model.dto.request.RoundUpdateDTO;
import com.ra.javaserviecproject.model.entity.RoundCriterion;

import java.util.List;

public interface RoundCriterionService {
    List<RoundCriterion> findAll();
    RoundCriterion findById(Integer id);
    RoundCriterion add(RoundCriterionDTO dto);
    RoundCriterion update(RoundUpdateDTO dto, Integer id);
    void delete(Integer id);
}