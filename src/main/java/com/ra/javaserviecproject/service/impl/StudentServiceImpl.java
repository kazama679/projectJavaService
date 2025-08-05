package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.StudentDTO;
import com.ra.javaserviecproject.model.dto.request.StudentUpdateDTO;
import com.ra.javaserviecproject.model.entity.Mentor;
import com.ra.javaserviecproject.model.entity.Student;
import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.repository.InternshipAssignmentRepository;
import com.ra.javaserviecproject.repository.MentorRepository;
import com.ra.javaserviecproject.repository.StudentRepository;
import com.ra.javaserviecproject.repository.UserRepository;
import com.ra.javaserviecproject.security.UserPrincipal;
import com.ra.javaserviecproject.service.InternshipAssignmentService;
import com.ra.javaserviecproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InternshipAssignmentRepository internshipRepository;

    @Override
    public Student add(StudentDTO student) {
        User user = userRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: " + student.getId()));
        boolean isStudent = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equalsIgnoreCase("STUDENT"));
        if (!isStudent) {
            throw new RuntimeException("User này không có quyền STUDENT");
        }
        if (studentRepository.existsById(student.getId())) {
            throw new RuntimeException("User này đã là sinh viên rồi");
        }
        if (studentRepository.existsByStudentCode(student.getStudentCode())) {
            throw new RuntimeException("Student Code đã được sử dụng bởi người khác");
        }
        Student newStudent = Student.builder()
                .user(user)
                .studentCode(student.getStudentCode())
                .major(student.getMajor())
                .className(student.getClassName())
                .dateOfBirth(student.getDateOfBirth())
                .address(student.getAddress())
                .build();
        return studentRepository.save(newStudent);
    }

    @Override
    public List<Student> findAll(UserPrincipal userPrincipal) {
        boolean isAdmin = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("ADMIN"));
        if (isAdmin) {
            return studentRepository.findAll();
        }
        boolean isMentor = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("MENTOR"));
        if (isMentor) {
            Integer mentorId = userPrincipal.getUser().getId();
            return internshipRepository.findStudentsByMentorId(mentorId);
        }
        throw new RuntimeException("Bạn không có quyền xem danh sách sinh viên");
    }

    @Override
    public Student findById(Integer id, UserPrincipal userPrincipal) {
        boolean isAdmin = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("ADMIN"));
        if (isAdmin) {
            return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
        }
        boolean isMentor = userPrincipal.getUser().getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("MENTOR"));
        if (isMentor) {
            Integer mentorId = userPrincipal.getUser().getId();
            boolean assigned = internshipRepository.existsByMentor_MentorIdAndStudent_StudentId(mentorId, id);
            if (!assigned) {
                throw new RuntimeException("Bạn không có quyền xem thông tin sinh viên này");
            }
            return studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
        }
        boolean isStudent = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("STUDENT"));
        if (isStudent) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
            if (!userPrincipal.getUser().getId().equals(id)) {
                throw new RuntimeException("Bạn không có quyền xem thông tin sinh viên khác");
            }
            return student;
        }
        throw new RuntimeException("Bạn không có quyền xem chi tiết sinh viên");
    }

    @Override
    public Student update(StudentUpdateDTO student, Integer id, UserPrincipal userPrincipal) {
        boolean isAdmin = userPrincipal.getUser().getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("ADMIN"));
        boolean isStudent = userPrincipal.getUser().getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("STUDENT"));
        if (isAdmin) {
            // Cho phép admin cập nhật bất kỳ sinh viên nào
        } else if (isStudent) {
            Student currentStudent = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
            if (!currentStudent.getUser().getId().equals(userPrincipal.getUser().getId())) {
                throw new RuntimeException("Bạn không có quyền chỉnh sửa thông tin sinh viên khác");
            }
        } else {
            throw new RuntimeException("Bạn không có quyền chỉnh sửa thông tin sinh viên");
        }
        if (studentRepository.existsByStudentCodeAndStudentIdNot(student.getStudentCode(), id)) {
            throw new RuntimeException("Student Code đã được sử dụng bởi người khác");
        }
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
        existingStudent.setStudentCode(student.getStudentCode());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setClassName(student.getClassName());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setAddress(student.getAddress());
        return studentRepository.save(existingStudent);
    }
}
