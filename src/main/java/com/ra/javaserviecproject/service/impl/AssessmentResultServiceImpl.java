package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.AssessmentResultDTO;
import com.ra.javaserviecproject.model.entity.*;
import com.ra.javaserviecproject.repository.*;
import com.ra.javaserviecproject.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {
    @Autowired
    private AssessmentResultRepository repo;
    @Autowired
    private InternshipAssignmentRepository internshipAssignmentRepo;
    @Autowired
    private AssessmentRoundRepository assessmentRoundRepo;
    @Autowired
    private EvaluationCriteriaRepository criteriaRepo;
    @Autowired
    private MentorRepository mentorRepo;

    @Override
    public AssessmentResult add(AssessmentResultDTO mentor) {
        InternshipAssignment internshipAssignment = internshipAssignmentRepo.findById(mentor.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phân công thực tập với ID: " + mentor.getAssignmentId()));
        AssessmentRound assessmentRound = assessmentRoundRepo.findById(mentor.getRoundId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đợt đánh giá với ID: " + mentor.getRoundId()));
        EvaluationCriteria criteria = criteriaRepo.findById(mentor.getCriterionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tiêu chí đánh giá với ID: " + mentor.getCriterionId()));
        Mentor mentorEntity = mentorRepo.findById(mentor.getEvaluatedBy())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giáo viên đánh giá với ID: " + mentor.getEvaluatedBy()));
        AssessmentResult assessmentResult = AssessmentResult.builder()
                .assignment(internshipAssignment)
                .round(assessmentRound)
                .criterion(criteria)
                .score(mentor.getScore())
                .comments(mentor.getComments())
                .mentor(mentorEntity)
                .evaluationDate(mentor.getEvaluationDate() != null ? mentor.getEvaluationDate() : LocalDateTime.now())
                .status(true)
                .build();
        return repo.save(assessmentResult);
    }

    @Override
    public List<AssessmentResult> findAll() {
        return repo.findAll();
    }

    @Override
    public AssessmentResult findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kết quả đánh giá với ID: " + id));
    }

    @Override
    public AssessmentResult update(AssessmentResultDTO mentor, Integer id) {
        InternshipAssignment internshipAssignment = internshipAssignmentRepo.findById(mentor.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phân công thực tập với ID: " + mentor.getAssignmentId()));
        AssessmentRound assessmentRound = assessmentRoundRepo.findById(mentor.getRoundId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đợt đánh giá với ID: " + mentor.getRoundId()));
        EvaluationCriteria criteria = criteriaRepo.findById(mentor.getCriterionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tiêu chí đánh giá với ID: " + mentor.getCriterionId()));
        Mentor mentorEntity = mentorRepo.findById(mentor.getEvaluatedBy())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giáo viên đánh giá với ID: " + mentor.getEvaluatedBy()));
        AssessmentResult existingResult = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kết quả đánh giá với ID: " + id));
        existingResult.setAssignment(internshipAssignment);
        existingResult.setRound(assessmentRound);
        existingResult.setCriterion(criteria);
        existingResult.setScore(mentor.getScore());
        existingResult.setComments(mentor.getComments());
        existingResult.setMentor(mentorEntity);
        existingResult.setEvaluationDate(mentor.getEvaluationDate() != null ? mentor.getEvaluationDate() : LocalDateTime.now());
        existingResult.setStatus(true);
        return repo.save(existingResult);
    }

    @Override
    public void delete(Integer id) {
        AssessmentResult existingResult = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kết quả đánh giá với ID: " + id));
        existingResult.setStatus(false);
        repo.save(existingResult);
    }
}
