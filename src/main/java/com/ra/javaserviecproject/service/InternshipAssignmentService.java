package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.InternshipAssignmentDTO;
import com.ra.javaserviecproject.model.entity.InternshipAssignment;

import java.util.List;

public interface InternshipAssignmentService {
    List<InternshipAssignment> findAll();
    InternshipAssignment findById(Integer id);
    InternshipAssignment add(InternshipAssignmentDTO student);
    InternshipAssignment update(InternshipAssignmentDTO student, Integer id);
    void delete(Integer id);
}
