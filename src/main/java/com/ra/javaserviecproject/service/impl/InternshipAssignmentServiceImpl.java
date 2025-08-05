package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.InternshipAssignmentDTO;
import com.ra.javaserviecproject.model.entity.*;
import com.ra.javaserviecproject.repository.InternshipAssignmentRepository;
import com.ra.javaserviecproject.repository.InternshipPhaseRepository;
import com.ra.javaserviecproject.repository.MentorRepository;
import com.ra.javaserviecproject.repository.StudentRepository;
import com.ra.javaserviecproject.service.InternshipAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InternshipAssignmentServiceImpl implements InternshipAssignmentService {
    @Autowired
    private InternshipAssignmentRepository repo;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private MentorRepository mentorRepo;
    @Autowired
    private InternshipPhaseRepository internshipPhaseRepo;

    @Override
    public InternshipAssignment add(InternshipAssignmentDTO dto) {
        Student studentEntity = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên với ID: " + dto.getStudentId()));
        Mentor mentorEntity = mentorRepo.findById(dto.getMentorId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giáo viên với ID: " + dto.getMentorId()));
        InternshipPhase internshipPhase = internshipPhaseRepo.findById(dto.getPhaseId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giai đoạn thực tập với ID: " + dto.getPhaseId()));
        InternshipAssignment internshipAssignment = InternshipAssignment.builder()
                .student(studentEntity)
                .mentor(mentorEntity)
                .phase(internshipPhase)
                .assignedDate(dto.getAssignedDate() != null ? dto.getAssignedDate() : LocalDateTime.now())
                .status(dto.getStatus() != null ? dto.getStatus() : AssignmentStatus.PENDING)
                .build();
        return repo.save(internshipAssignment);
    }

    @Override
    public List<InternshipAssignment> findAll() {
        return repo.findAll();
    }

    @Override
    public InternshipAssignment findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phân công thực tập với ID: " + id));
    }

    @Override
    public InternshipAssignment update(InternshipAssignmentDTO student, Integer id) {
        InternshipAssignment existingAssignment = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phân công thực tập với ID: " + id));
        Student studentEntity = studentRepo.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên với ID: " + student.getStudentId()));
        Mentor mentorEntity = mentorRepo.findById(student.getMentorId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giáo viên với ID: " + student.getMentorId()));
        InternshipPhase internshipPhase = internshipPhaseRepo.findById(student.getPhaseId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giai đoạn thực tập với ID: " + student.getPhaseId()));
        existingAssignment.setStudent(studentEntity);
        existingAssignment.setMentor(mentorEntity);
        existingAssignment.setPhase(internshipPhase);
        if (student.getStatus() != null) {
            existingAssignment.setStatus(student.getStatus());
        }
        return repo.save(existingAssignment);
    }

    @Override
    public void delete(Integer id) {
        InternshipAssignment existingAssignment = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phân công thực tập với ID: " + id));
        existingAssignment.setStatus(AssignmentStatus.CANCELLED);
        repo.save(existingAssignment);
    }
}
