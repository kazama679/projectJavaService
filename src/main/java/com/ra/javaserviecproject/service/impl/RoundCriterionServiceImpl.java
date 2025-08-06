package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.RoundCriterionDTO;
import com.ra.javaserviecproject.model.dto.request.RoundUpdateDTO;
import com.ra.javaserviecproject.model.entity.AssessmentRound;
import com.ra.javaserviecproject.model.entity.EvaluationCriteria;
import com.ra.javaserviecproject.model.entity.RoundCriterion;
import com.ra.javaserviecproject.repository.AssessmentRoundRepository;
import com.ra.javaserviecproject.repository.EvaluationCriteriaRepository;
import com.ra.javaserviecproject.repository.RoundCriterionRepository;
import com.ra.javaserviecproject.service.RoundCriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundCriterionServiceImpl implements RoundCriterionService {
    @Autowired
    private RoundCriterionRepository repo;
    @Autowired
    private AssessmentRoundRepository assessmentRoundRepo;
    @Autowired
    private EvaluationCriteriaRepository evaluationCriteriaRepo;

    @Override
    public RoundCriterion add(RoundCriterionDTO dto) {
        AssessmentRound assessmentRound = assessmentRoundRepo.findById(dto.getRoundId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đợt đánh giá với ID: " + dto.getRoundId()));
        EvaluationCriteria evaluationCriteria = evaluationCriteriaRepo.findById(dto.getCriterionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tiêu chí đánh giá với ID: " + dto.getCriterionId()));
        RoundCriterion newRoundCriterion = RoundCriterion.builder()
                .round(assessmentRound)
                .criterion(evaluationCriteria)
                .weight(dto.getWeight())
                .status(true)
                .build();
        return repo.save(newRoundCriterion);
    }

    @Override
    public List<RoundCriterion> findAll() {
        return repo.findAll();
    }

    @Override
    public RoundCriterion findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tiêu chí của đợt đánh giá với ID: " + id));
    }

    @Override
    public RoundCriterion update(RoundUpdateDTO dto, Integer id) {
        RoundCriterion existingRoundCriterion = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tiêu chí của đợt đánh giá với ID: " + id));
        existingRoundCriterion.setWeight(dto.getWeight());
        return repo.save(existingRoundCriterion);
    }

    @Override
    public void delete(Integer id) {
        RoundCriterion roundCriterion = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tiêu chí của đợt đánh giá với ID: " + id));
        roundCriterion.setStatus(false);
        repo.save(roundCriterion);
    }
}
