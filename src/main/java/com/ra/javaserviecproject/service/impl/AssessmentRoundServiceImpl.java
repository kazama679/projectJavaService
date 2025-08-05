package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.AssessmentRoundDTO;
import com.ra.javaserviecproject.model.entity.AssessmentRound;
import com.ra.javaserviecproject.model.entity.InternshipPhase;
import com.ra.javaserviecproject.repository.AssessmentRoundRepository;
import com.ra.javaserviecproject.repository.InternshipPhaseRepository;
import com.ra.javaserviecproject.service.AssessmentRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentRoundServiceImpl implements AssessmentRoundService {
    @Autowired
    private AssessmentRoundRepository repo;
    @Autowired
    private InternshipPhaseRepository internshipPhaseRepo;

    @Override
    public List<AssessmentRound> findAll() {
        return repo.findAll();
    }

    @Override
    public AssessmentRound findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đợt đánh giá với id: " + id));
    }

    @Override
    public AssessmentRound add(AssessmentRoundDTO student) {
        InternshipPhase internshipPhase = internshipPhaseRepo.findById(student.getPhaseId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giai đoạn thực tập với id: " + student.getPhaseId()));
        AssessmentRound newAssessmentRound = AssessmentRound.builder()
                .phase(internshipPhase)
                .roundName(student.getRoundName())
                .startDate(student.getStartDate())
                .endDate(student.getEndDate())
                .description(student.getDescription())
                .isActive(true)
                .build();
        return repo.save(newAssessmentRound);
    }

    @Override
    public AssessmentRound update(AssessmentRoundDTO dto, Integer id) {
        AssessmentRound assessmentRound = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đợt đánh giá với id: " + id));
        assessmentRound.setRoundName(dto.getRoundName());
        assessmentRound.setStartDate(dto.getStartDate());
        assessmentRound.setEndDate(dto.getEndDate());
        assessmentRound.setDescription(dto.getDescription());
        return repo.save(assessmentRound);
    }

    @Override
    public void delete(Integer id) {
        AssessmentRound assessmentRound = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đợt đánh giá với id: " + id));
        assessmentRound.setIsActive(false);
        repo.save(assessmentRound);
    }

    @Override
    public List<AssessmentRound> findAllByPhaseId(Integer phaseId) {
        return repo.findAllByPhase_PhaseId(phaseId);
    }
}
