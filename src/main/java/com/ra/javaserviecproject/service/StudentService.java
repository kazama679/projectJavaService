package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.StudentDTO;
import com.ra.javaserviecproject.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Integer id);
    Student add(StudentDTO student);
    Student update(StudentDTO student, Integer id);
    void delete(Integer id);
}
