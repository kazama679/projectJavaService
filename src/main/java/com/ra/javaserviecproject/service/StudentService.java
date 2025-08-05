package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.StudentDTO;
import com.ra.javaserviecproject.model.dto.request.StudentUpdateDTO;
import com.ra.javaserviecproject.model.entity.Student;
import com.ra.javaserviecproject.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface StudentService {
    List<Student> findAll(@AuthenticationPrincipal UserPrincipal userPrincipal);
    Student findById(Integer id, @AuthenticationPrincipal UserPrincipal userPrincipal);
    Student add(StudentDTO student);
    Student update(StudentUpdateDTO student, Integer id, @AuthenticationPrincipal UserPrincipal userPrincipal);
}
