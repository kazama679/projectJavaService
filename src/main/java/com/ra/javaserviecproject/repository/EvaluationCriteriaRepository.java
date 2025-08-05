package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.EvaluationCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria, Integer> {
    boolean existsByCriterionName(String criterionName);
    boolean existsByCriterionNameAndCriterionIdNot(String criterionName, Integer criterionId);
}