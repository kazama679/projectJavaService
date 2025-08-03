package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.StudentDTO;
import com.ra.javaserviecproject.model.entity.Student;
import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.repository.StudentRepository;
import com.ra.javaserviecproject.repository.UserRepository;
import com.ra.javaserviecproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Student add(StudentDTO student) {
        User user = userRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: " + student.getId()));
        Student newStudent = Student.builder()
                .studentCode(student.getStudentCode())
                .major(student.getMajor())
                .className(student.getClassName())
                .dateOfBirth(student.getDateOfBirth())
                .address(student.getAddress())
                .user(user)
                .build();
        return studentRepository.save(newStudent);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
    }

    @Override
    public Student update(StudentDTO student, Integer id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
        existingStudent.setStudentCode(student.getStudentCode());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setClassName(student.getClassName());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setAddress(student.getAddress());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void delete(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy giáo viên với id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
