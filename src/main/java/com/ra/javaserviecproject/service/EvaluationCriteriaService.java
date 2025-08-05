package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.EvaluationCriteriaDTO;
import com.ra.javaserviecproject.model.entity.EvaluationCriteria;

import java.util.List;

public interface EvaluationCriteriaService {
    List<EvaluationCriteria> findAll();
    EvaluationCriteria findById(Integer id);
    EvaluationCriteria add(EvaluationCriteriaDTO student);
    EvaluationCriteria update(EvaluationCriteriaDTO student, Integer id);
    void delete(Integer id);
}
