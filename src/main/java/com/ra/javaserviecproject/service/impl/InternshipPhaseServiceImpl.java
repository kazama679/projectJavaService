package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.InternshipPhaseDTO;
import com.ra.javaserviecproject.model.entity.InternshipPhase;
import com.ra.javaserviecproject.repository.InternshipPhaseRepository;
import com.ra.javaserviecproject.service.InternshipPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InternshipPhaseServiceImpl implements InternshipPhaseService {
    @Autowired
    private InternshipPhaseRepository internshipPhaseRepository;

    @Override
    public InternshipPhase add(InternshipPhaseDTO internshipPhase) {
        if (internshipPhaseRepository.existsByPhaseNameIgnoreCase(internshipPhase.getPhaseName())) {
            throw new RuntimeException("Tên giai đoạn đã tồn tại");
        }
        InternshipPhase newInternshipPhase = InternshipPhase.builder()
                .phaseName(internshipPhase.getPhaseName())
                .startDate(internshipPhase.getStartDate())
                .endDate(internshipPhase.getEndDate())
                .description(internshipPhase.getDescription())
                .status(true)
                .build();
        return internshipPhaseRepository.save(newInternshipPhase);
    }

    @Override
    public List<InternshipPhase> findAll() {
        return internshipPhaseRepository.findAll();
    }

    @Override
    public InternshipPhase findById(Integer id) {
        return internshipPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giai đoạn thực tập với id: " + id));
    }

    @Override
    public InternshipPhase update(InternshipPhaseDTO mentor, Integer id) {
        InternshipPhase existingInternshipPhase = internshipPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giai đoạn thực tập với id: " + id));
        if (internshipPhaseRepository.existsByPhaseNameAndPhaseIdNot(mentor.getPhaseName(), id)) {
            throw new RuntimeException("Tên giai đoạn đã tồn tại");
        }
        existingInternshipPhase.setPhaseName(mentor.getPhaseName());
        existingInternshipPhase.setStartDate(mentor.getStartDate());
        existingInternshipPhase.setEndDate(mentor.getEndDate());
        existingInternshipPhase.setDescription(mentor.getDescription());
        existingInternshipPhase.setStatus(true);
        return internshipPhaseRepository.save(existingInternshipPhase);
    }

    @Override
    public void delete(Integer id) {
        InternshipPhase internshipPhase = internshipPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giai đoạn thực tập với id: " + id));
        internshipPhase.setStatus(false);
        internshipPhaseRepository.save(internshipPhase);
    }
}
