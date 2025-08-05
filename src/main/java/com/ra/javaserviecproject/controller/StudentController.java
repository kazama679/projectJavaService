package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.StudentUpdateDTO;
import com.ra.javaserviecproject.security.UserPrincipal;
import com.ra.javaserviecproject.service.StudentService;
import com.ra.javaserviecproject.model.dto.request.StudentDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<APIResponse<Student>> add(@Valid @RequestBody StudentDTO studentDTO) {
        Student newStudent = studentService.add(studentDTO);
        return ResponseEntity.status(201).body(
                APIResponse.<Student>builder().message("Đăng ký sinh viên thành công").data(newStudent).success(true).timestamp(LocalDateTime.now()).build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Student>>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(
                APIResponse.<List<Student>>builder()
                        .message("Lấy danh sách sinh viên thành công")
                        .success(true)
                        .data(studentService.findAll(userPrincipal))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> getUserById(@PathVariable Integer id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(
                APIResponse.<Student>builder()
                        .message("Lấy thông tin sinh viên viên thành công")
                        .success(true)
                        .data(studentService.findById(id, userPrincipal))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> updateUser(@PathVariable Integer id, @Valid @RequestBody StudentUpdateDTO dto, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(
                APIResponse.<Student>builder()
                        .message("Cập nhập sinh viên thành công")
                        .success(true)
                        .data(studentService.update(dto, id, userPrincipal))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
