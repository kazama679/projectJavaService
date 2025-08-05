package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.EvaluationCriteriaDTO;
import com.ra.javaserviecproject.model.entity.EvaluationCriteria;
import com.ra.javaserviecproject.repository.EvaluationCriteriaRepository;
import com.ra.javaserviecproject.service.EvaluationCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationCriteriaServiceImpl implements EvaluationCriteriaService {
    @Autowired
    private EvaluationCriteriaRepository repo;

    @Override
    public EvaluationCriteria add(EvaluationCriteriaDTO student) {
        EvaluationCriteria newCriteria = EvaluationCriteria.builder()
                .criterionName(student.getCriterionName())
                .description(student.getDescription())
                .maxScore(student.getMaxScore())
                .status(true)
                .build();
        return repo.save(newCriteria);
    }

    @Override
    public List<EvaluationCriteria> findAll() {
        return repo.findAll();
    }

    @Override
    public EvaluationCriteria findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tiêu chí đánh giá với id: " + id));
    }

    @Override
    public EvaluationCriteria update(EvaluationCriteriaDTO student, Integer id) {
        EvaluationCriteria existingCriteria = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tiêu chí đánh giá với id: " + id));
        existingCriteria.setCriterionName(student.getCriterionName());
        existingCriteria.setDescription(student.getDescription());
        existingCriteria.setMaxScore(student.getMaxScore());
        return repo.save(existingCriteria);
    }

    @Override
    public void delete(Integer id) {
        EvaluationCriteria existingCriteria = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tiêu chí đánh giá với id: " + id));
        existingCriteria.setStatus(false);
        repo.save(existingCriteria);
    }
}
